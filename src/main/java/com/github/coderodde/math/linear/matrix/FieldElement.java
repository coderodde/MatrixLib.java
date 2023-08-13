package com.github.coderodde.math.linear.matrix;

/**
 * This interface defines the API of the elements representing a field.
 * 
 * @param <E> the target element type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public interface FieldElement<E> extends Negable<E>, 
                                         Addable<E>, 
                                         Multipliable<E>,
                                         Zero<E> {
    
}
