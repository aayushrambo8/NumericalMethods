package Module1;

import java.util.*;

public class BisectionMethod
{
    static double equation(int[] X, double x)
    {
        return X[0] * Math.pow(x, 0)
                + X[1] * Math.pow(x, 1)
                + X[2] * Math.pow(x, 2)
                + X[3] * Math.pow(x, 3)
                + X[4] * Math.pow(x, 4);
    }

    static void bijectionMethod(double leftInterval, double rightInterval, int[] X, double epsilon)
    {
        double middle = 0;
        double previousMiddle;
        System.out.println("\nIteration\t\tLeft\t\t\tRight\t\t\t\tMiddle\t\t\t\tf(middle)\n");
        for (int i = 1; ; i++)
        {
            previousMiddle = middle;
            middle = (leftInterval + rightInterval) / 2;
            System.out.printf("%d.\t\t\t%.15f\t%.15f\t%.15f\t%.15f\n",
                    i, rightInterval, leftInterval, middle, equation(X, middle));
            if (i > 1 && Math.abs(middle - previousMiddle) < epsilon)
                break;
            if (equation(X, leftInterval) * equation(X, middle) < 0)
                rightInterval = middle;
            else
                leftInterval = middle;
        }
        System.out.println("\nApproximate root = " + middle);
    }

    public static void main(String[] args)
    {
        Scanner obj = new Scanner(System.in);
        int[] X = new int[5];
        int i;
        System.out.println("Enter the coefficients of x with the respective powers: \nIt supports powers up to 4");
        for (i = 0; i <= 4; i++)
        {
            System.out.printf("x to %d:", i);
            X[i] = obj.nextInt();
        }
        System.out.println("Enter 2 values between which the root might be.");
        double xa = obj.nextDouble();
        double xb = obj.nextDouble();
        System.out.println("Enter the number of decimal places your answer should approximate to.");
        int epsilon = obj.nextInt();
        double toleranceValue = Math.pow(10, -epsilon);
        if (equation(X, xa) * equation(X, xb) >= 0)
            System.out.println("Choose a different pair of values of because roots may or may not exist");
        else
            bijectionMethod(xa, xb, X, toleranceValue);
    }
}