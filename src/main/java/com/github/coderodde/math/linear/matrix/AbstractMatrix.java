package com.github.coderodde.math.linear.matrix;

import java.util.Objects;

/**
 * This abstract class defines the API for the matrix data types.
 * 
 * @param <M> the actual implementing matrix type.
 * @param <E> the matrix element type.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public abstract class AbstractMatrix<M extends AbstractMatrix<M, E>, E> {
    
    /**
     * The width of this matrix.
     */
    protected final int width;
    
    /**
     * The height of this matrix.
     */
    protected final int height;
    
    /**
     * The field element API.
     */
    protected final FieldElement<E> fieldElements;
    
    protected AbstractMatrix(int width, 
                             int height, 
                             FieldElement<E> fieldElements) {
        
        this.width = checkWidth(width);
        this.height = checkHeight(height);
        this.fieldElements = Objects.requireNonNull(fieldElements);;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets each element in this matrix to its negative.
     */
    public abstract void negate();
    
    /**
     * Returns a copy of this matrix with each element negated. After this 
     * operation, this matrix remains intact.
     * 
     * @return new, negated matrix.
     */
    public abstract M immutableNegate();
    
    /**
     * Adds the input matrix to this matrix.
     * 
     * @param other the matrix to add to this matrix. 
     */
    public abstract void add(M other);
    
    /**
     * Returns a copy of this matrix with elements from {@code other} added to 
     * it. After this operation, this matrix remains intact.
     * 
     * @param other the matrix to add.
     * 
     * @return copy of this matrix with input elements added.
     */
    public abstract M immutableAdd(M other);
    
    /**
     * Creates a new matrix and sets it to the product of this and {@code right}
     * matrices. After this operation, this matrix remains intact.
     * 
     * @param right the right hand matrix in the product. This matrix is the 
     *              left hand matrix.
     * 
     * @return the matrix product.
     */
    public abstract M multiply(M right);
    
    /**
     * Returns the element at {@code y}th row, {@code x}th column.
     * 
     * @param x the {@code X}-coordinate of the element.
     * @param y the {@code Y}-coordinate of the element.
     * 
     * @return the matrix element at specified coordinates.
     */
    public abstract E get(int x, int y);
    
    /**
     * Sets the value {@code value} at {@code y}th row, {@code x}th column.
     * 
     * @param x     the {@code X}-coordinate of the value.
     * @param y     the {@code Y}-coordinate of the value.
     * @param value the value to set.
     */
    public abstract void set(int x, int y, E value);
    
    /**
     * Checks whether {@code o} is an abstract matrix and has the same content
     * as this matrix.
     * 
     * @param o the matrix to check against.
     * @return {@code true} only if the two matrices are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (!Objects.requireNonNull(o).getClass().equals(this.getClass())) {
            return false;
        }
        
        AbstractMatrix other = (AbstractMatrix) o;
        
        if (width != other.width || height != other.height) {
            return false;
        }
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!get(x, y).equals(other.get(x, y))) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    protected static int checkWidth(int widthCandidate) {
        if (widthCandidate == 0) {
            throw new IllegalArgumentException("Matrix width is zero.");
        }
        
        if (widthCandidate < 0) {
            throw new IllegalArgumentException(
                    "Matrix width is negative: " + widthCandidate);
        }
        
        return widthCandidate;
    }
    
    protected static int checkHeight(int heightCandidate) {
        if (heightCandidate == 0) {
            throw new IllegalArgumentException("Matrix width is zero.");
        }
        
        if (heightCandidate < 0) {
            throw new IllegalArgumentException(
                    "Matrix width is negative: " + heightCandidate);
        }
        
        return heightCandidate;
    }
    
    protected void checkCoordinates(int x, int y) {
        checkX(x);
        checkY(y);
    }
    
    private void checkX(int x) {
        if (x < 0) {
            throw new IndexOutOfBoundsException(
                    "X-coordinate is negative: " + x);
        }
        
        if (x >= width) {
            throw new IndexOutOfBoundsException(
                    "X-coordinate is too large: " 
                            + x
                            + ". Must be at most " 
                            + (width - 1) 
                            + ".");
        }
    }
    
    private void checkY(int y) {
        if (y < 0) {
            throw new IndexOutOfBoundsException(
                    "Y-coordinate is negative: " + y);
        }
        
        if (y >= height) {
            throw new IndexOutOfBoundsException(
                    "Y-coordinate is too large: " 
                            + y
                            + ". Must be at most " 
                            + (height - 1) 
                            + ".");
        }
    }
    
    protected void checkMatrixHaveSameDimensions(M matrix1, M matrix2) {
        if (matrix1.getWidth() != matrix2.getWidth()) {
            throw new MatricesNotAddableException(
                    "Matrix widths mismatch: " 
                            + matrix1.getWidth() 
                            + " vs " 
                            + matrix2.getWidth() 
                            + ".");
        }
        
        if (matrix1.getHeight() != matrix2.getHeight()) {
            throw new MatricesNotAddableException(
                    "Matrix heights mismatch: " 
                            + matrix1.getHeight() 
                            + " vs " 
                            + matrix2.getHeight() 
                            + ".");
        }
    }
        
    protected void checkMatricesCanBeMultiplied(M leftMatrix, M rightMatrix) {
        if (leftMatrix.getWidth() != rightMatrix.getHeight()) {
            throw new MatricesNotMultipliableException(
                    "Cannot multiply the matrices. Width of left matrix is "
                            + leftMatrix.getWidth() 
                            + ", the height of the right matrix is " 
                            + rightMatrix.getHeight() 
                            + ".");
        }
    }
}
