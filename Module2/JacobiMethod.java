package Module2;

import java.util.Scanner;

public class JacobiMethod
{

    static void readMatrix(Scanner obj, double[][] A, String title,int col)
{
    System.out.printf("Enter Elements of %s (M)",title);
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < col; j++)
        {
            System.out.printf("M%d%d: ", i + 1, j + 1);
            A[i][j] = obj.nextDouble();
        }
}

    static void readInitialGuess(Scanner obj, double[][] X)
    {
        System.out.println("\nEnter initial guesses for x, y and z:");
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
        System.out.println("\nSolution using Jacobi Method:");
        char c = 'x';
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("%c = %.6f\n", c, X[i][0]);
            c++;
        }
    }

    static void jacobiMethod(double[][] A, double[][] B, double[][] X, int maxIter, double tol)
    {
        double[][] xOld = new double[3][1];
        System.out.println("\nIteration\t x\t\t y\t\t z");

        for (int iteration = 1; iteration <= maxIter; iteration++)
        {
            for (int i = 0; i < 3; i++)
                xOld[i][0] = X[i][0];

            for (int i = 0; i < 3; i++)
            {
                if (A[i][i] == 0)
                {
                    System.out.println("Zero diagonal element encountered. Jacobi cannot proceed.");
                    return;
                }

                double sum = B[i][0];
                for (int j = 0; j < 3; j++)
                {
                    if (j != i)
                        sum -= A[i][j] * xOld[j][0];
                }
                X[i][0] = sum / A[i][i];
            }

            System.out.printf("%d\t\t %.6f\t %.6f\t %.6f\n",iteration, X[0][0], X[1][0], X[2][0]);

            double err = 0.0;
            for (int i = 0; i < 3; i++)
                err = Math.max(err, Math.abs(X[i][0] - xOld[i][0]));

            if (err < tol)
            {
                System.out.println("\nConverged in " + iteration + " iterations.");
                break;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        System.out.println("Jacobi Method (3x3)");

        double[][] A = new double[3][3];
        double[][] B = new double[3][1];
        double[][] X = new double[3][1];

        readMatrix(obj, A,"Matrix A",3);
        readMatrix(obj, B,"Matrix B",1);
        readInitialGuess(obj, X);

        System.out.println("Enter no. of required Decimal Places and max Iterations");
        int epsilon = obj.nextInt();
        int maxIteration = obj.nextInt();
        double tolerance = Math.pow(10,-epsilon);

        jacobiMethod(A, B, X, maxIteration, tolerance);

        printSolution(X);
    }
}
