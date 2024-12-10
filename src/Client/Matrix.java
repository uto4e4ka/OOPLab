package Client;

import Exeptions.MatrixSizeOutOfBound;
import Exeptions.MatrixWrongSize;

import java.io.Serializable;

public class Matrix implements Serializable {
    private double[][] matrix;
    public Matrix (int rows,int columns){
        if(rows<0||columns<0)throw new MatrixWrongSize(rows+" "+columns);
        this.matrix = new double[rows][columns];
    }
    public int getRows(){
        return matrix.length;
    }
    public int getColumns(){
        return matrix[0].length;
    }
    public void set(int row,int column,double value){
        if(isRightIndex(row, column))matrix[row][column] = value;
        else throw new MatrixSizeOutOfBound(row+" "+column);
    }
    public double get(int row, int column){
        if(isRightIndex(row, column)) return matrix[row][column];
        else throw new MatrixSizeOutOfBound(row+" "+column);
    }
    private boolean isRightIndex(int row,int column){
        return row <= getRows() - 1 && column <= getColumns() - 1;
    }

    public static double getOddSum(Matrix matrix){
        double sum = Double.NaN;
        for (int i=0;i<matrix.getRows();i++){
            for (int k=0;k<matrix.getColumns();k++){
                double num = matrix.get(i,k);
                if(num%2!=0) sum+=num;
            }
        }
        return sum;
    }
}
