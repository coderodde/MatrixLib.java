package com.github.coderodde.math.linear.matrix.support;

import com.github.coderodde.math.linear.matrix.FieldElement;
import java.math.BigInteger;

/**
 * This class implements the field elements over the set of {@link BigInteger}
 * values.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public class BigIntegerFieldElement implements FieldElement<BigInteger> {

    @Override
    public BigInteger negate(BigInteger value) {
        return value.negate();
    }

    @Override
    public BigInteger add(BigInteger valueLeft, BigInteger valueRight) {
        return valueLeft.add(valueRight);
    }

    @Override
    public BigInteger multiply(BigInteger valueLeft, BigInteger valueRight) {
        return valueLeft.multiply(valueRight);
    }

    @Override
    public BigInteger identity() {
        return BigInteger.ZERO;
    }
}
