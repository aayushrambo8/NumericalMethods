package Module1;

import java.util.Scanner;

public class NumericalMethods
{
    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        printMenu();
        int choice = obj.nextInt();
        int[] X = new int[5];
        System.out.println("coefficients of x with the respective powers (0 to 4):");
        for (int i = 0; i <= 4; i++)
        {
            System.out.printf("x to %d: ", i);
            X[i] = obj.nextInt();
        }
        if (choice == 4)
        {
            System.out.println("Enter initial guess:");
            double xa = obj.nextDouble();
            System.out.print("Enter number of decimal places: ");
            int epsilon = obj.nextInt();
            epsilon++;
            newtonRaphsonMethod(xa, X, epsilon);
        } else
        {
            System.out.println("Enter 2 values between which the root might be:");
            double xa = obj.nextDouble();
            double xb = obj.nextDouble();
            System.out.print("Enter number of decimal places: ");
            int epsilon = obj.nextInt();
            epsilon++;
            double toleranceValue = Math.pow(10, -epsilon);

            if (equation(X, xa) * equation(X, xb) >= 0)
                System.out.printf("Root may not exist in this interval (f(%f)*f(%f) >= 0).", xa, xb);
            else
            {
                switch (choice)
                {
                    case 1:
                        bijectionMethod(xa, xb, X, toleranceValue, epsilon);
                        break;
                    case 2:
                        regulaFalsiMethod(xa, xb, X, epsilon);
                        break;
                    case 3:
                        secantMethod(xa, xb, X, epsilon);
                        break;
                    default:
                        System.out.println("Invalid Choice.");
                }
            }
        }
    }

    static double roundToEpsilon(double value, int epsilon)
    {
        double scale = Math.pow(10, epsilon);
        return Math.round(value * scale) / scale;
    }

    static double equation(int[] X, double x)
    {
        double sum = 0;
        for (int i = 0; i < X.length; i++) sum += X[i] * Math.pow(x, i);
        return sum;
    }

    static double derivative(int[] X, double x)
    {
        double sum = 0;
        for (int i = 1; i < X.length; i++) sum += i * X[i] * Math.pow(x, i - 1);
        return sum;
    }

    static void bijectionMethod(double a, double b, int[] X, double tolerance, int epsilon)
    {
        double m = 0, previousMiddle;
        System.out.println("\nIter\tLeft\t\tRight\t\tMiddle\t\tf(m)");
        for (int i = 1; ; i++)
        {
            previousMiddle = m;
            m = (a + b) / 2;
            System.out.printf("%d.\t%.8f\t%.8f\t%.8f\t%.8f\n", i, a, b, m, equation(X, m));
            if (i > 1 && Math.abs(m - previousMiddle) < tolerance) break;
            if (equation(X, a) * equation(X, m) < 0) b = m;
            else a = m;
        }
        System.out.println("\nApproximate root = " + roundToEpsilon(m, epsilon));
    }

    static void regulaFalsiMethod(double leftInterval, double rightInterval, int[] X, int epsilon)
    {
        double middle = 0, previousMiddle;
        double tolerance = Math.pow(10, -(epsilon + 1));
        System.out.println("\nIteration\tLeft\t\t\t\tRight\t\t\t\tMiddle");
        for (int i = 1; ; i++)
        {
            previousMiddle = middle;
            double fLeft = equation(X, leftInterval);
            double fRight = equation(X, rightInterval);
            middle = (leftInterval * fRight - rightInterval * fLeft) / (fRight - fLeft);
            System.out.printf("%d.\t\t\t%.15f\t%.15f\t%.15f\n",
                    i, leftInterval, rightInterval, middle);
            if (i > 1 && Math.abs(middle - previousMiddle) < tolerance)
                break;
            if (fLeft * equation(X, middle) < 0)
                rightInterval = middle;
            else
                leftInterval = middle;
        }
        System.out.println("\nApproximate root = " + roundToEpsilon(middle, epsilon));
    }

    static void secantMethod(double x0, double x1, int[] X, int epsilon)
    {
        double x2 = 0, prevX2;
        double tolerance = Math.pow(10, -epsilon);
        System.out.println("\nIteration\tx(n-1)\t\t\t\tx(n)\t\t\t\tx(n+1)");
        for (int i = 1; ; i++)
        {
            prevX2 = x2;
            double fa = equation(X, x0);
            double fb = equation(X, x1);
            if (fb - fa == 0) break;
            x2 = (x0 * fb - x1 * fa) / (fb - fa);
            System.out.printf("%d.\t\t\t%.15f\t%.15f\t%.15f\n",
                    i, x0, x1, x2);
            if (i > 1 && Math.abs(x2 - prevX2) < tolerance) break;
            x0 = x1;
            x1 = x2;
        }
        System.out.println("\nApproximate root = " + roundToEpsilon(x2, epsilon));
    }

    static void newtonRaphsonMethod(double x0, int[] X, int epsilon)
    {
        double x1 = x0, x2;
        double tolerance = Math.pow(10, -epsilon);
        System.out.println("\nIteration\tx(n)\t\t\t\tx(n+1)");
        for (int i = 1; ; i++)
        {
            double f = equation(X, x1);
            double df = derivative(X, x1);
            if (df == 0) return;
            x2 = x1 - (f / df);
            System.out.printf("%d.\t\t\t%.15f\t%.15f\n", i, x1, x2);
            if (Math.abs(x2 - x1) < tolerance)
            {
                x1 = x2;
                break;
            }
            x1 = x2;
        }
        System.out.println("\nApproximate root = " + roundToEpsilon(x1, epsilon));
    }

    static void printMenu()
    {
        System.out.println("\n--- Numerical Methods Suite ---");
        System.out.println("""
                1. Bisection Method
                2. Regula Falsi Method
                3. Secant Method
                4. Newton-Raphson Method""");
        System.out.print("Choice: ");
    }
}