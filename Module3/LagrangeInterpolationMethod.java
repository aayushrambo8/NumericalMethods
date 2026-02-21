package Module3;

import java.util.*;

public class LagrangeInterpolationMethod
{
    static void lagrangeInterpolationMethod(double [] x, double[] y, double number, int n)
    {
        double fx = 0;
        for(int i = 0; i<n;i++)
        {
            double p = 1;
            for(int j = 0;j<n;j++)
                if(i!=j)
                    p*=(number-x[j])/(x[i]-x[j]);
            fx+=p*y[i];
        }
        System.out.printf("Value of Y at X = %f is %f",number,fx);
    }

    public static void main(String[] ags)
    {
        Scanner obj = new Scanner(System.in);
        int n;
        System.out.println("Lagrange Interpolation");
        System.out.print("Enter the number of data points:");
        n = obj.nextInt();
        double[] x = new double [n];
        double[] y = new double [n];
        System.out.println("Enter the values of X and Y");
        for(int i = 0; i<n;i++)
        {
            System.out.printf("X%d:",i);
            x[i] = obj.nextDouble();
            System.out.printf("Y%d:",i);
            y[i] = obj.nextDouble();
        }
        System.out.print("Enter the value at which interpolation is required: ");
        double number = obj.nextDouble();
        lagrangeInterpolationMethod(x, y, number, n);
    }
}