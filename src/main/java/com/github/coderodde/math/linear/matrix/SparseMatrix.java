package com.github.coderodde.math.linear.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a sparse matrix.
 * 
 * @param <E> the matrix element type.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Aug 13, 2023)
 * @since 1.6 (Aug 13, 2023)
 */
public class SparseMatrix<E> extends AbstractMatrix<SparseMatrix<E>, E> {
    
    private final Map<Integer, Map<Integer, E>> dataXY = new HashMap<>();
    private final Map<Integer, Map<Integer, E>> dataYX = new HashMap<>();
    
    public SparseMatrix(int width, int height, FieldElement<E> fieldElements) {
        super(width, height, fieldElements);
    }

    @Override
    public void negate() {
        for (Map.Entry<Integer, Map<Integer, E>> entry1 : dataXY.entrySet()) {
            for (Map.Entry<Integer, E> entry2 : entry1.getValue().entrySet()) {
                entry2.setValue(fieldElements.negate(entry2.getValue()));
            }
        }
    }

    @Override
    public SparseMatrix<E> immutableNegate() {
        SparseMatrix<E> ret = new SparseMatrix<>(width, height, fieldElements);
        
        for (Map.Entry<Integer, Map<Integer, E>> entry1 : dataXY.entrySet()) {
            int x = entry1.getKey();
            
            for (Map.Entry<Integer, E> entry2 : entry1.getValue().entrySet()) {
                int y = entry2.getKey();
                
                ret.set(x, y, fieldElements.negate(entry2.getValue()));
            }
        }
        
        return ret;
    }

    @Override
    public void add(SparseMatrix<E> other) {
        for (Map.Entry<Integer, Map<Integer, E>> entry1
                : other.dataXY.entrySet()) {
            int x = entry1.getKey();
            
            for (Map.Entry<Integer, E> entry2 : entry1.getValue().entrySet()) {
                int y = entry2.getKey();
                
                set(x, y, fieldElements.add(get(x, y), entry2.getValue()));
            }
        }
    }

    @Override
    public SparseMatrix<E> immutableAdd(SparseMatrix<E> other) {
        SparseMatrix<E> ret = new SparseMatrix<>(width, height, fieldElements);
        
        for (Map.Entry<Integer, Map<Integer, E>> entry1 : dataXY.entrySet()) {
            int x = entry1.getKey();
            
            for (Map.Entry<Integer, E> entry2 : entry1.getValue().entrySet()) {
                int y = entry2.getKey();
                
                ret.set(x, y, entry2.getValue());
            }
        }
        
        for (Map.Entry<Integer, Map<Integer, E>> entry1
                : other.dataXY.entrySet()) {
            int x = entry1.getKey();
            
            for (Map.Entry<Integer, E> entry2 : entry1.getValue().entrySet()) {
                int y = entry2.getKey();
                
                ret.set(x, 
                        y, 
                        fieldElements.add(ret.get(x, y), 
                                          other.get(x, y)));
            }
        }
        
        return ret;
    }

    @Override
    public SparseMatrix<E> multiply(SparseMatrix<E> right) {
        checkMatricesCanBeMultiplied(this, right);
        SparseMatrix<E> ret = new SparseMatrix<>(width, height, fieldElements);
    
        for (int leftRow = 0; leftRow < height; leftRow++) {
            for (int rightColumn = 0; 
                    rightColumn < right.width;
                    rightColumn++) {
                
                ret.set(rightColumn,
                        leftRow, 
                        combineRowCol(dataYX.get(leftRow),
                                      right.dataXY.get(rightColumn)));
            }
        }
        
        return ret;
    }

    @Override
    public E get(int x, int y) {
        if (!dataXY.containsKey(x)) {
            return fieldElements.identity();
        }
        
        return dataXY.get(x).getOrDefault(y, fieldElements.identity());
    }

    @Override
    public void set(int x, int y, E value) {
        if (value == null || value.equals(fieldElements.identity())) {
            deleteZeroEntry(x, y);
        } else {
            updateEntry(x, y, value);
        }
    }
    
    private void deleteZeroEntry(int x, int y) {
        if (dataXY.containsKey(x)) {
            dataXY.get(x).remove(y);
            
            if (dataXY.get(x).isEmpty()) {
                dataXY.remove(x);
            }
        }
        
        if (dataYX.containsKey(y)) {
            dataYX.get(y).remove(x);
            
            if (dataYX.get(y).isEmpty()) {
                dataYX.remove(y);
            }
        }
    }
    
    private void updateEntry(int x, int y, E value) {
        if (!dataXY.containsKey(x)) {
            Map<Integer, E> subMap = new HashMap<>();
            subMap.put(y, value);
            dataXY.put(x, subMap);
        } else {
            dataXY.get(x).put(y, value);
        }
        
        if (!dataYX.containsKey(y)) {
            Map<Integer, E> subMap = new HashMap<>();
            subMap.put(x, value);
            dataYX.put(y, subMap);
        } else {
            dataYX.get(y).put(x, value);
        }
    }
    
    private E combineRowCol(Map<Integer, E> colMap, Map<Integer, E> rowMap) {
        E sum = fieldElements.identity();
        
        if (colMap == null || rowMap == null) {
            return sum;
        }
        
        for (Map.Entry<Integer, E> entry : rowMap.entrySet()) {
            int a = entry.getKey();
            E rowValue = entry.getValue();
            
            if (colMap.containsKey(a)) {
                E columnValue = colMap.get(a);
                E product = fieldElements.multiply(columnValue, rowValue);
                sum = fieldElements.add(sum, product);
            }
        }
        
        return sum;
    }
}
