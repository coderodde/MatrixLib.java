package com.github.coderodde.math.linear.matrix;

/**
 * Objects of this exception class are thrown when it is not possible to add two
 * matrices due to dimension mismatch.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 9, 2023)
 * @since 1.6 (Aug 9, 2023)
 */
public final class MatricesNotAddableException extends RuntimeException {
    
    public MatricesNotAddableException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
