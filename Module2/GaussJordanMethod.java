package Module2;

import java.util.Scanner;

public class GaussJordanMethod
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
        System.out.println("\nMatrix B");
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%8.4f\n", M[i][0]);
        }
    }

    static void printSolution(double[][] B)
    {
        System.out.println("\nSolution of AX = B using Gauss-Jordan:");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c = %.6f\n", c, B[i][0]);
            c++;
        }
    }

    static void gaussJordan(double[][] A, double[][] B)
    {
        for (int k = 0; k < 3; k++)
        {
            double pivot = A[k][k];

            for (int j = 0; j < 3; j++)
                A[k][j] = A[k][j] / pivot;
            B[k][0] = B[k][0] / pivot;

            for (int i = 0; i < 3; i++)
            {
                if (i != k)
                {
                    double factor = A[i][k];
                    for (int j = 0; j < 3; j++)
                        A[i][j] = A[i][j] - factor * A[k][j];
                    B[i][0] = B[i][0] - factor * B[k][0];
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        System.out.println("Gauss-Jordan Method (3x3)");

        double[][] A = new double[3][3];
        double[][] B = new double[3][1];

        readMatrixA(obj, A);
        readMatrixB(obj, B);

        printMatrix("Matrix A (Input):", A);
        printMatrixB(B);

        gaussJordan(A, B);

        printMatrix("Matrix A (After Gauss-Jordan → Identity):", A);
        printMatrixB(B);

        printSolution(B);
    }
}
