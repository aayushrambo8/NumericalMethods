package Module3;

import java.util.Scanner;

public class LagrangeInterpolationMethod
{
    static Scanner obj = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.print("Enter number of data points: ");
        int n = obj.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];

        System.out.println("Enter x and y values:");
        for (int i = 0; i < n; i++)
        {
            x[i] = obj.nextDouble();
            y[i] = obj.nextDouble();
        }

        System.out.print("Enter value of x to interpolate: ");
        double xp = obj.nextDouble();

        double yp = 0;

        for (int i = 0; i < n; i++)
        {
            double term = y[i];
            for (int j = 0; j < n; j++)
            {
                if (j != i)
                {
                    term *= (xp - x[j]) / (x[i] - x[j]);
                }
            }
            yp += term;
        }

        System.out.println("Interpolated value at x = " + xp + " is: " + yp);
    }
}
