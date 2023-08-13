package com.github.coderodde.math.linear.matrix;

/**
 * This interface defines the operation of producing an additive identity.
 * 
 * @param <E> the target element type.
 * @author Rodion "rodio" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public interface Zero<E> {
    
    /**
     * Returns the additive identity. For many fields, this will be the zero 
     * value.
     * 
     * @return additive identity.
     */
    E identity();
}
