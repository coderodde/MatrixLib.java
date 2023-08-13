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
    
    @Test
    public void add() {
        Integer[][] data1 = {
            {1, 2},
            {3, 4},
        };
        
        Integer[][] data2 = {
            {1, 2},
            {3, 5},
        };
        
        Integer[][] expected = {
            {2, 4},
            {6, 9},
        };
        
        DenseMatrix2D<Integer> m1 = 
                MatrixBuilder.buildDenseMatrix(
                        data1,
                        new IntegerFieldElement());
        
        DenseMatrix2D<Integer> m2 = 
                MatrixBuilder.buildDenseMatrix(
                        data2,
                        new IntegerFieldElement());
        
        m1.add(m2);
        
        DenseMatrix2D<Integer> em =
                MatrixBuilder.buildDenseMatrix(
                        expected, 
                        new IntegerFieldElement());
        
        assertEquals(em, m1);
    }
    
    @Test
    public void multiply() {
        Integer[][] data1 = {
            {1, 0},
            {0, 2},
        };
        
        Integer[][] data2 = {
            {2, 4},
            {3, 0},
        };
        
        Integer[][] expected = {
            {2, 4},
            {6, 0},
        };
        
        DenseMatrix2D<Integer> expectedResult = 
                MatrixBuilder.buildDenseMatrix(
                        expected,
                        new IntegerFieldElement());
        
        DenseMatrix2D<Integer> m1 = 
            MatrixBuilder.buildDenseMatrix(
                    data1, 
                    new IntegerFieldElement());
        
        DenseMatrix2D<Integer> m2 = 
            MatrixBuilder.buildDenseMatrix(
                    data2, 
                    new IntegerFieldElement());
        
        DenseMatrix2D<Integer> result = m1.multiply(m2);
        
        assertEquals(expectedResult, result);
    }
}
