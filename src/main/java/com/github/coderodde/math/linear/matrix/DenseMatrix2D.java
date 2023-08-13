package com.github.coderodde.math.linear.matrix;

/**
 * This class implements a (dense) matrix stored as a two-dimensional array.
 * 
 * @param <E> the matrix element type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public class DenseMatrix2D<E> extends AbstractMatrix<DenseMatrix2D<E>, E> {
    
    /**
     * The actual matrix holding the elements.
     */
    private final E[][] data;

    /**
     * Constructs a new dense matrix that stores all the elements in a two-
     * dimensional array.
     * 
     * @param width         the width of this matrix.
     * @param height        the height of this matrix.
     * @param fieldElements the field element API object.
     */
    public DenseMatrix2D(int width, int height, FieldElement<E> fieldElements) {
        super(width, height, fieldElements);
        
        data = (E[][]) new Object[height][];
        
        for (int y = 0; y < height; y++) {
            data[y] = (E[]) new Object[width];
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public E get(int x, int y) {
        checkCoordinates(x, y);
        return data[y][x];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void set(int x, int y, E value) {
        if (value == null || fieldElements.identity().equals(value)) {
            data[y][x] = fieldElements.identity();
        } else {
            data[y][x] = value;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void negate() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, fieldElements.negate(get(x, y)));
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DenseMatrix2D<E> immutableNegate() {
        DenseMatrix2D<E> ret = new DenseMatrix2D<>(width,
                                                   height,
                                                   fieldElements);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ret.set(x, y, fieldElements.negate(get(x, y)));
            }
        }
        
        return ret;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void add(DenseMatrix2D<E> other) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                set(x, y, fieldElements.add(get(x, y), other.get(x, y)));
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DenseMatrix2D<E> immutableAdd(DenseMatrix2D<E> other) {
        DenseMatrix2D<E> ret = new DenseMatrix2D<>(width, 
                                                   height,
                                                   fieldElements);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ret.set(x, y, fieldElements.add(get(x, y), other.get(x, y)));
            }
        }
        
        return ret;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DenseMatrix2D<E> multiply(DenseMatrix2D<E> right) {
        DenseMatrix2D<E> ret = new DenseMatrix2D<>(right.getWidth(),
                                                   this.getHeight(),
                                                   fieldElements);
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < right.getWidth(); col++) {
                ret.set(col, row, combineRowColumn(col, row, right));
            }
        }
        
        return ret;
    }
    
    private E combineRowColumn(int col, int row, DenseMatrix2D<E> right) {
        E sum = fieldElements.identity();
        
        for (int x = 0; x < width; x++) {
            E product = fieldElements.multiply(get(x, row), right.get(col, x));
            sum = fieldElements.add(sum, product);
        }
        
        return sum;
    }
}
