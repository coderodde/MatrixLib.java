package com.github.coderodde.math.linear.matrix;

/**
 * This interface defines the operation of multiplication of the two given 
 * values.
 * 
 * @param <E> the target element type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public interface Multipliable<E> {
    
    /**
     * Implements the element multiplication.
     * 
     * @param valueLeft  the left hand value.
     * @param valueRight the right hand value.
     * @return the product of the two input values.
     */
    E multiply(E valueLeft, E valueRight);
}
