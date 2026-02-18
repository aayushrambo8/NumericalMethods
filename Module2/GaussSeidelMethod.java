package Module2;

import java.util.Scanner;

public class GaussSeidelMethod
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

    static void readInitialGuess(Scanner obj, double[][] X)
    {
        System.out.println("\nEnter initial guesses for x");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c0: ", c);
            X[i][0] = obj.nextDouble();
            c++;
        }
    }

    static void printSolution(double[][] X)
    {
        System.out.println("\nSolution using Gauss-Seidel Method:");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c = %.6f\n", c, X[i][0]);
            c++;
        }
    }

    static void seidelMethod(double[][] A, double[][] B, double[][] X, int maxIter, double tol)
    {
        double[][] xOld = new double[3][1];
        System.out.println("\nIteration\t x\t\t\t y\t\t\t z");
        for (int iter = 1; iter <= maxIter; iter++)
        {
            for (int i = 0; i < 3; i++)
                xOld[i][0] = X[i][0];
            for (int i = 0; i < 3; i++)
            {
                if (A[i][i] == 0)
                {
                    System.out.println("Zero diagonal element encountered. Seidel cannot proceed.");
                    return;
                }
                double sum = B[i][0];
                for (int j = 0; j < 3; j++)
                {
                    if (j != i)
                        sum -= A[i][j] * X[j][0];
                }
                X[i][0] = sum / A[i][i];
            }

            System.out.printf("%d.\t\t\t %.6f\t %.6f\t %.6f\n",
                    iter, X[0][0], X[1][0], X[2][0]);
            double err = 0.0;
            for (int i = 0; i < 3; i++)
                err = Math.max(err, Math.abs(X[i][0] - xOld[i][0]));
            if (err < tol)
            {
                System.out.printf("\nConverged in %d iterations.\n",iter);
                break;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        System.out.println("Gauss-Seidel Method");

        double[][] A = new double[3][3];
        double[][] B = new double[3][1];
        double[][] X = new double[3][1];

        readMatrixA(obj, A);
        readMatrixB(obj, B);
        readInitialGuess(obj, X);

        int maxIter = 50;
        double tol = 1e-6;

        seidelMethod(A, B, X, maxIter, tol);
        printSolution(X);
    }
}
