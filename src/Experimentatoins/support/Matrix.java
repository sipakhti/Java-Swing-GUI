package Experimentatoins.support;

public abstract class Matrix {


    public static long[][] addMatrix(int[][] matrixA, int[][] matrixB) {
        long[][] matrixSum = new long[matrixA.length][matrixA[0].length];
        boolean canAdd = matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
        if (canAdd) {
            for (int row = 0;row < matrixA.length;row++) {
                for (int column = 0; column < matrixA[0].length; column++) {
                    matrixSum[row][column] = matrixA[row][column] + matrixB[row][column];
                }
            }
        }
        return matrixSum;
    }

    public static int[][] subtractMatrix(int[][] matrixA, int[][] matrixB) {
        int[][] matrixSum = new int[matrixA.length][matrixA[0].length];
        boolean cansubtract = matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
        if (cansubtract) {
            for (int row = 0;row < matrixA.length;row++) {
                for (int column = 0; column < matrixA[0].length; column++) {
                    matrixSum[row][column] = matrixA[row][column] - matrixB[row][column];
                }
            }
        }
        return matrixSum;
    }

    public static long[][] multiplyMatrix(int[][] matrixA, int[][] matrixB) {
        long[][] productMatrix = new long[matrixA.length][matrixB[0].length];
        boolean canMultiply = matrixA[0].length == matrixB.length;

        if (canMultiply) {
            for (int row = 0; row < matrixA.length;row++)
                for (int column = 0; column < matrixB[0].length; column++) {
                    for (int n = 0; n < matrixB.length; n++) {
                        productMatrix[row][column] += matrixA[row][n] * matrixB[n][column];
                    }
                }
        }
        return productMatrix;
    }

    public static double[][] inverseMatrix(int[][] matrix) {
        /** calculates the inverse of the given matrix
         * This achieved by doing elementary row operations on the whole matrix until it becomes an identity matrix
         * and same operations are also done on the identity matrix.
         *
         * @param matrix: 2D Array of integers
         *
         * @return inverse matrix
         */


        // initialize the identity matrix
        double[][] identity_matrix = new double[matrix.length][matrix[0].length];
        // populate the identity matrix array to look like an identity matrix
        for (int i = 0; i < identity_matrix.length; i++) {
            identity_matrix[i][i] = 1;
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length ; column++) {
                if (matrix[row][row] == 1)
                    break;
                    // Exchange the rows. if the next value in the next row is still 0 then switch with the next row and so on.
                else if (matrix[row][row] == 0) {
                    int[] temp = matrix[row];
                    double[] idnentity_temp = identity_matrix[row];
                    try {
                        matrix[row] = matrix[row + 1];
                        identity_matrix[row] = identity_matrix[row+1];
                        identity_matrix[row+1] = idnentity_temp;
                        matrix[row+1] = temp;
                        //                      printMatrix(matrix);
                    } catch (IndexOutOfBoundsException e){
                        return null;
                    }

                }
                else {
                    int pivot_value = matrix[row][row];
                    for (int i = 0; i < matrix[0].length; i++) {
                        identity_matrix[row][i] *= (1.0/ pivot_value);
                        matrix[row][i] *= (1.0 / pivot_value);
                    }

                }
            }
            // to make all the values in the column downwards 0
            for (int i = row+1; i < matrix.length; i++) {
                if (matrix[i][row] == 0)
                    continue;
                else {
                    int temp = matrix[i][row];
                    for (int j = 0; j < matrix[0].length ; j++) {
                        identity_matrix[i][j] -= temp * identity_matrix[row][j];
                        matrix[i][j] -= temp * matrix[row][j];
                    }
                }
            }
        }

        return gaussJordan(matrix,identity_matrix);
    }

    private static double[][] gaussJordan(int[][] matrix,double[][] identity_matrix) {
        // initialize the identity matrix



        for (int row = matrix.length-1; row >= 0; row--) {

            // to make all the values in the column downwards 0
            for (int i = row-1; i >= 0; i--) {
                if (matrix[i][row] == 0)
                    continue;
                else {
                    int temp = matrix[i][row];
                    for (int j = 0; j < matrix[0].length ; j++) {
                        // Adding a multiple of one row to another row
                        identity_matrix[i][j] -= temp * identity_matrix[row][j];
                        matrix[i][j] -= temp * matrix[row][j];
                    }
                }
            }
        }
        return identity_matrix;
    }

}

