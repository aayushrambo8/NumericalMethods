package Module1;

import java.util.*;
public class RegulaFalsiMethod
{
    static double equation(int[] X, double x)
    {
        return X[0] * Math.pow(x, 0)
                + X[1] * Math.pow(x, 1)
                + X[2] * Math.pow(x, 2)
                + X[3] * Math.pow(x, 3)
                + X[4] * Math.pow(x, 4);
    }
    static void regulaFalsiMethod(double leftInterval, double rightInterval, int[] X, int epsilon)
    {
        double middle = 0;
        double previousMiddle;
        boolean continueLoop = true;
        System.out.println("\nIteration\tLeft\t\t\t\tRight\t\t\t\tMiddle");
        for (int i = 1; continueLoop; i++)
        {
            previousMiddle = middle;

            middle = (leftInterval * equation(X, rightInterval)
                    - rightInterval * equation(X, leftInterval))
                    / (equation(X, rightInterval) - equation(X, leftInterval));

            System.out.printf("%d.\t\t\t%.15f\t%.15f\t%.15f\n",i, leftInterval, rightInterval, middle);
            if (equation(X, leftInterval) * equation(X, middle) < 0)
                rightInterval = middle;
            else
                leftInterval = middle;

            if (i > 1 && Math.abs(middle - previousMiddle) < Math.pow(10, -epsilon))
                continueLoop = false;
        }
        double scale = Math.pow(10, epsilon);
        middle = Math.round(middle * scale) / scale;
        System.out.println("\nApproximate root = "+middle);
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
        if (equation(X, xa) * equation(X, xb) >= 0)
            System.out.println("Choose a different pair of values of because roots may or may not exist");
        else
            regulaFalsiMethod(xa, xb, X, epsilon);
    }
}