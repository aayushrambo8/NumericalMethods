package Module2;

import java.util.Scanner;

public class LUDecompositionMethod
{
    static void readMatrix(Scanner obj, double[][] A, String title, int col)
    {
        System.out.printf("Enter Elements of %s (M)\n", title);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < col; j++)
            {
                System.out.printf("M%d%d: ", i + 1, j + 1);
                A[i][j] = obj.nextDouble();
            }
    }

    static void printMatrix(String title, double[][] M, int col)
    {
        System.out.println("\n" + title);
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < col; j++)
                System.out.printf("%.4f  ", M[i][j]);
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

        readMatrix(obj, A, "Matrix A", 3);
        readMatrix(obj, B, "Matrix B", 1);
        printMatrix("Matrix A", A, 3);
        printMatrix("Matrix B", B, 1);
        decomposeLU(A, L, U);

        double[][] Y = new double[3][1];
        forwardSubstitution(L, B, Y);

        double[][] X = new double[3][1];
        backSubstitution(U, Y, X);

        printMatrix("Lower Triangular Matrix L:", L, 3);
        printMatrix("Upper Triangular Matrix U:", U, 3);
        printSolution(X);
    }
}