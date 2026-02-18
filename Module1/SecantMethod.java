package Module1;

import java.util.*;
public class SecantMethod
{
    static double equation(int[] X, double x)
    {
        return X[0] * Math.pow(x, 0)
                + X[1] * Math.pow(x, 1)
                + X[2] * Math.pow(x, 2)
                + X[3] * Math.pow(x, 3)
                + X[4] * Math.pow(x, 4);
    }
    static void secantMethod(double x0, double x1, int[] X, int epsilon)
    {
        double x2 = 0;
        double previousRoot = 0;
        double scale = Math.pow(10, epsilon);
        System.out.println("\nIteration\tx(n-1)\t\t\t\tx(n)\t\t\t\tx(n+1)");
        for (int i = 1; ; i++)
        {
            previousRoot = x2;
            x2 = (x0 * equation(X, x1) - x1 * equation(X, x0))
                    / (equation(X, x1) - equation(X, x0));
            System.out.printf("%d.\t\t\t%.15f\t%.15f\t%.15f\n",
                    i, x0, x1, x2);
            if (i > 1 && Math.round(previousRoot * scale) == Math.round(x2 * scale))
                break;
            x0 = x1;
            x1 = x2;
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
        System.out.println("Enter 2 initial values:");
        double xa = obj.nextDouble();
        double xb = obj.nextDouble();
        System.out.print("Enter number of decimal places: ");
        int epsilon = obj.nextInt();
        secantMethod(xa, xb, X, epsilon);
    }
}