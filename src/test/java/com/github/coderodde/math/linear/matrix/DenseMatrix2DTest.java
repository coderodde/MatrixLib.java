package com.github.coderodde.math.linear.matrix;

import com.github.coderodde.math.linear.matrix.support.IntegerFieldElement;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DenseMatrix2DTest {
    
    @Test
    public void negate() {
        Integer[][] data = {
            {2, 0},
            {1, 3},
        };
        
        Integer[][] expected = {
            {-2, 0},
            {-1, -3},
        };
        
        DenseMatrix2D<Integer> m =
                MatrixBuilder.buildDenseMatrix(data, new IntegerFieldElement());
        
        m.negate();
        
        DenseMatrix2D<Integer> em =
                MatrixBuilder.buildDenseMatrix(expected, 
                                               new IntegerFieldElement());
        
        assertEquals(em, m);
    }
}
