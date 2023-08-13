package com.github.coderodde.math.linear.matrix;

/**
 * This interface defines the type that given an instance of {@link Number}
 * produces its negative value.
 * 
 * @param <E> the target element type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public interface Negable<E> {
    
    /**
     * Negates the input value.
     * 
     * @param value the value to negate.
     * @return the negative of {@code value}.
     */
    E negate(E value);
}
