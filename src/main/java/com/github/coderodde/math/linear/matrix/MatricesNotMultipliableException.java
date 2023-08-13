package com.github.coderodde.math.linear.matrix;

/**
 * Instances of this exception class are thrown when it is not possible to 
 * multiply a couple of matrices.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 9, 2023)
 * @since 1.6 (Aug 9, 2023)
 */
public final class MatricesNotMultipliableException extends RuntimeException {
    
    public MatricesNotMultipliableException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
