package com.github.coderodde.math.linear.matrix.support;

import com.github.coderodde.math.linear.matrix.FieldElement;

/**
 * This class implements the field elements over the set of {@code int} values.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public class IntegerFieldElement implements FieldElement<Integer> {
    
    private static final Integer ZERO = Integer.valueOf(0);

    @Override
    public Integer negate(Integer value) {
        return value == null ? ZERO : Integer.valueOf(-value);
    }

    @Override
    public Integer add(Integer valueLeft, Integer valueRight) {
        return valueLeft + valueRight;
    }

    @Override
    public Integer multiply(Integer valueLeft, Integer valueRight) {
        return valueLeft * valueRight;
    }

    @Override
    public Integer identity() {
        return ZERO;
    }
}
