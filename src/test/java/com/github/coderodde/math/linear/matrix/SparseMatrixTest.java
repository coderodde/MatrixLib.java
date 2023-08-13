package com.github.coderodde.math.linear.matrix;

import com.github.coderodde.math.linear.matrix.support.IntegerFieldElement;
import org.junit.Test;
import static org.junit.Assert.*;

public class SparseMatrixTest {
    
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
        
        SparseMatrix<Integer> m =
                MatrixBuilder.buildSparseMatrix(data, new IntegerFieldElement());
        
        m.negate();
        
        SparseMatrix<Integer> em =
                MatrixBuilder.buildSparseMatrix(expected, 
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
        
        SparseMatrix<Integer> m1 = 
                MatrixBuilder.buildSparseMatrix(
                        data1,
                        new IntegerFieldElement());
        
        SparseMatrix<Integer> m2 = 
                MatrixBuilder.buildSparseMatrix(
                        data2,
                        new IntegerFieldElement());
        
        m1.add(m2);
        
        SparseMatrix<Integer> em =
                MatrixBuilder.buildSparseMatrix(
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
        
        SparseMatrix<Integer> expectedResult = 
                MatrixBuilder.buildSparseMatrix(
                        expected,
                        new IntegerFieldElement());
        
        SparseMatrix<Integer> m1 = 
            MatrixBuilder.buildSparseMatrix(
                    data1, 
                    new IntegerFieldElement());
        
        SparseMatrix<Integer> m2 = 
            MatrixBuilder.buildSparseMatrix(
                    data2, 
                    new IntegerFieldElement());
        
        SparseMatrix<Integer> result = m1.multiply(m2);
        
        assertEquals(expectedResult, result);
    }
}
