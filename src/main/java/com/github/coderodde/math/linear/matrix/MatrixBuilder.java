package com.github.coderodde.math.linear.matrix;

/**
 *
 * @author rodio
 */
public final class MatrixBuilder {
    
    public static <E> SparseMatrix<E> buildSparseMatrix(
            E[][] data, 
            FieldElement<E> fieldElements) {
        int height = data.length;
        int width = getLargestWidth(data);
        
        SparseMatrix<E> ret = new SparseMatrix<>(width, height, fieldElements);
        
        for (int y = 0; y < data.length; y++) {
            if (data[y] == null) {
                continue;
            }
            
            for (int x = 0; x < data[y].length; x++) {
                ret.set(x, y, data[y][x]);
            }
        }
        
        return ret;
    }
    
    public static <E> DenseMatrix2D<E> buildDenseMatrix(
            E[][] data, 
            FieldElement<E> fieldElements) {
        int height = data.length;
        int width = getLargestWidth(data);
        
        DenseMatrix2D<E> ret = new DenseMatrix2D<>(width,
                                                   height,
                                                   fieldElements);
        
        for (int y = 0; y < data.length; y++) {
            if (data[y] == null) {
                continue;
            }
            
            for (int x = 0; x < data[y].length; x++) {
                ret.set(x, y, data[y][x]);
            }
        }
        
        return ret;
    }
    
    private static <E> int getLargestWidth(E[][] data) {
        int maximumRowWidth = 0;
        
        for (E[] row : data) {
            int tentativeRowWidth = row == null ? 0 : row.length;
            maximumRowWidth = Math.max(maximumRowWidth, tentativeRowWidth);
        }
        
        return maximumRowWidth;
    }
}
