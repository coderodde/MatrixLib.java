package com.github.coderodde.math.linear.matrix;

/**
 * This exception class implements the type for throwing when a matrix is not a
 * square matrix.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 15, 2023)
 * @since 1.6 (Aug 15, 2023)
 */
public class MatrixIsNotSquareMatrixException extends RuntimeException {
    
    public MatrixIsNotSquareMatrixException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
