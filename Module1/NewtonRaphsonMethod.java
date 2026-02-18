package Module1;

import java.util.Scanner;

public class NewtonRaphsonMethod
{
    static double equation(int[] X, double x)
    {
        double sum = 0;
        for (int i = 0; i < X.length; i++)
            sum += X[i] * Math.pow(x, i);
        return sum;
    }

    static double derivative(int[] X, double x)
    {
        double sum = 0;
        for (int i = 1; i < X.length; i++)
            sum += i * X[i] * Math.pow(x, i - 1);
        return sum;
    }
    static void newtonRaphsonMethod(double x0, int[] X, int epsilon)
    {
        double previousRoot = x0,x2,scale = Math.pow(10, epsilon);
        System.out.println("Iteration\t x(n)\t\t\t x(n+1)");
        for (int i = 1; ; i++)
        {
            double f = equation(X, previousRoot);
            double df = derivative(X, previousRoot);
            if (df == 0)
            {
                System.out.println("Derivative zero. Method fails.");
                return;
            }
            x2 = previousRoot - (f / df);
            System.out.printf("%d\t\t%.15f\t%.15f\n", i, previousRoot, x2);
            if (i > 1 &&
                    Math.round(previousRoot * scale) == Math.round(x2 * scale))
                break;
            previousRoot = x2;
        }
        x2 = Math.round(x2 * scale) / scale;
        System.out.println("\nApproximate root = " + x2);
    }
    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        int[] X = new int[5];
        System.out.println("Enter the coefficients of x with the respective powers:");
        for (int i = 0; i <= 4; i++)
        {
            System.out.printf("x to %d: ", i);
            X[i] = obj.nextInt();
        }
        System.out.println("Enter initial guess:");
        double xa = obj.nextDouble();
        System.out.print("Enter number of decimal places: ");
        int epsilon = obj.nextInt();
        newtonRaphsonMethod(xa, X, epsilon);
    }
}
