package Module2;

import java.util.Scanner;

public class LUDecompositionMethod
{
    static void readMatrixA(Scanner obj, double[][] A)
    {
        System.out.println("Enter the matrix formed as A");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            {
                System.out.printf("A%d%d: ", i + 1, j + 1);
                A[i][j] = obj.nextDouble();
            }
    }

    static void readMatrixB(Scanner obj, double[][] B)
    {
        System.out.println("\nEnter the matrix formed as B");
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("B%d1: ", i + 1);
            B[i][0] = obj.nextDouble();
        }
    }

    static void printMatrix(String title, double[][] M)
    {
        System.out.println("\n" + title);
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
                System.out.printf("%8.4f  ", M[i][j]);
            System.out.println();
        }
    }

    static void printMatrixB(double[][] M)
    {
        System.out.println("\n" + "Matrix B");
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 1; j++)
                System.out.printf("%8.4f  ", M[i][j]);
            System.out.println();
        }
    }

    static void printSolution(double[][] X)
    {
        System.out.println("\n" + "Solution of AX = B using LU Decomposition:");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c = %.6f\n", c, X[i][0]);
            c++;
        }
    }

    static void decomposeLU(double[][] A, double[][] L, double[][] U)
    {
        for (int i = 0; i < 3; i++)
        {
            L[i][i] = 1;
            for (int k = i; k < 3; k++)
            {
                double sum = 0.0;
                for (int j = 0; j < i; j++)
                    sum += (L[i][j] * U[j][k]);
                U[i][k] = A[i][k] - sum;
            }

            for (int k = i; k < 3; k++)
            {
                double sum = 0.0;
                for (int j = 0; j < i; j++)
                    sum += (L[k][j] * U[j][i]);
                L[k][i] = (A[k][i] - sum) / U[i][i];
            }
        }
    }

    static void forwardSubstitution(double[][] L, double[][] B, double[][] Y)
    {
        for (int i = 0; i < 3; i++)
        {
            double sum = 0;
            for (int j = 0; j < i; j++)
                sum += L[i][j] * Y[j][0];
            Y[i][0] = (B[i][0] - sum) / L[i][i];
        }
    }

    static void backSubstitution(double[][] U, double[][] Y, double[][] X)
    {
        for (int i = 3 - 1; i >= 0; i--)
        {
            double sum = 0;
            for (int j = i + 1; j < 3; j++)
                sum += U[i][j] * X[j][0];
            X[i][0] = (Y[i][0] - sum) / U[i][i];
        }
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        System.out.println("LU Decomposition Method (Fully Modular, 3x3)");

        double[][] A = new double[3][3];
        double[][] B = new double[3][1];
        double[][] L = new double[3][3];
        double[][] U = new double[3][3];

        readMatrixA(obj, A);
        readMatrixB(obj, B);

        decomposeLU(A, L, U);

        double[][] Y = new double[3][1];
        forwardSubstitution(L, B, Y);

        double[][] X = new double[3][1];
        backSubstitution(U, Y, X);

        printMatrix("Matrix A", A);
        printMatrixB(B);
        printMatrix("Lower Triangular Matrix L:", L);
        printMatrix("Upper Triangular Matrix U:", U);
        printSolution(X);
    }
}