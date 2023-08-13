package com.github.coderodde.math.linear.matrix;

/**
 * This interface defines the operation of adding two values into one.
 * 
 * @param <E> the target element type.
 * @author Rodion "rodio" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public interface Addable<E> {
    
    /**
     * Implements the element addition.
     * 
     * @param valueLeft  the left hand value.
     * @param valueRight the right hand value.
     * @return the sum of the two input values.
     */
    E add(E valueLeft, E valueRight);
}
