package com.github.coderodde.math.linear.matrix;

import com.github.coderodde.math.linear.matrix.support.IntegerFieldElement;
import java.util.Random;

public class Demo {
    
    private static final long SEED = 123L;
    private static final int WIDTH = 1_000;
    private static final int HEIGHT = 1_000;
    private static final int ELEMENT_COUNT = 1_000;
    private static final IntegerFieldElement FIELD_ELEMENT =
            new IntegerFieldElement();
    
    private static final int MAX_ELEMENT_VALUE = 10_000;
    
    public static void main(String[] args) {
        warmup();
        benchmark();
    }
    
    private static void warmup() {
        System.out.println("Warming up...");
        profile(false);
    }
    
    private static void benchmark() {
        System.out.println("Benchmarking...");
        profile(true);
    }
    
    private static void profile(boolean print) {
        long ta = System.currentTimeMillis();
        DenseMatrix2D<Integer> denseMatrix = createDenseMatrix();
        long tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println("Created dense matrix in " + (tb - ta) + " ms.");
        }
        
        ta = System.currentTimeMillis();
        SparseMatrix<Integer> sparseMatrix = createSparseMatrix();
        tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println(
                    "Created sparse matrix in " + (tb - ta) + " ms.");
        }
        
        DenseMatrix2D<Integer> denseMatrix2 = createDenseMatrix();
        
        ta = System.currentTimeMillis();
        denseMatrix.add(denseMatrix2);
        tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println(
                    "Dense matrix addition in " + (tb - ta) + " ms.");
        }
        
        SparseMatrix<Integer> sparseMatrix2 = createSparseMatrix();
        
        ta = System.currentTimeMillis();
        sparseMatrix.add(sparseMatrix2);
        tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println(
                    "Sparse matrix addition in " + (tb - ta) + " ms.");
            
            System.out.println(
                    "Addition matches: " + denseMatrix.equals(sparseMatrix));
        }
        
        ta = System.currentTimeMillis();
        
        DenseMatrix2D<Integer> denseMatrix3 =
                denseMatrix.multiply(denseMatrix2);
        
        tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println(
                    "Dense matrix multiplication in " + (tb - ta) + " ms.");
        }
        
        ta = System.currentTimeMillis();
        
        SparseMatrix<Integer> sparseMatrix3 = 
                sparseMatrix.multiply(sparseMatrix2);
        
        tb = System.currentTimeMillis();
        
        if (print) {
            System.out.println(
                    "Sparse matrix multiplication in " + (tb - ta) + " ms.");
            
            System.out.println(
                    "Multiplication matches: " + 
                            denseMatrix3.equals(sparseMatrix3));
        }
        
    }
    
    private static DenseMatrix2D<Integer> createDenseMatrix() {
        Random random = new Random(SEED);
        DenseMatrix2D<Integer> out = new DenseMatrix2D<>(WIDTH, 
                                                         HEIGHT,
                                                         FIELD_ELEMENT);
        
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            int x = random.nextInt(out.getWidth());
            int y = random.nextInt(out.getHeight());
            int value = random.nextInt(MAX_ELEMENT_VALUE);
            out.set(x, y, value);
        }
        
        return out;
    }
    
    private static SparseMatrix<Integer> createSparseMatrix() {
        Random random = new Random(SEED);
        SparseMatrix<Integer> out = new SparseMatrix<>(WIDTH, 
                                                         HEIGHT,
                                                         FIELD_ELEMENT);
        
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            int x = random.nextInt(out.getWidth());
            int y = random.nextInt(out.getHeight());
            int value = random.nextInt(MAX_ELEMENT_VALUE);
            out.set(x, y, value);
        }
        
        return out;
    }
}
