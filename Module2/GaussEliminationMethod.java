package Module2;

import java.util.Scanner;

public class GaussEliminationMethod
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
                System.out.printf("%.4f  ", M[i][j]);
            System.out.println();
        }
    }

    static void printSolution(double[][] X)
    {
        System.out.println("\n" + "Solution of AX = B using Gauss Elimination:");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c = %.6f\n", c, X[i][0]);
            c++;
        }
    }

    static void gaussElimination(double[][] A, double[][] B)
    {
        for (int k = 0; k < 3; k++)
        {
            for (int i = k + 1; i < 3; i++)
            {
                double factor = A[i][k] / A[k][k];
                for (int j = k; j < 3; j++)
                    A[i][j] = A[i][j] - factor * A[k][j];
                B[i][0] = B[i][0] - factor * B[k][0];
            }
        }
    }

    static void backSubstitution(double[][] A, double[][] B, double[][] X)
    {
        for (int i = 3 - 1; i >= 0; i--)
        {
            double sum = 0;
            for (int j = i + 1; j < 3; j++)
                sum += A[i][j] * X[j][0];
            X[i][0] = (B[i][0] - sum) / A[i][i];
        }
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        System.out.println("Gauss Elimination Method (3x3)");

        double[][] A = new double[3][3];
        double[][] B = new double[3][1];

        readMatrixA(obj, A);
        readMatrixB(obj, B);

        gaussElimination(A, B);

        double[][] X = new double[3][1];
        backSubstitution(A, B, X);

        printMatrix("Matrix A", A);
        printMatrixB(B);
        printMatrix("Upper Triangular Matrix (After Elimination):", A);
        printSolution(X);
    }
}