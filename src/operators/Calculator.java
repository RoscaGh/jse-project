package operators;

import java.util.Scanner;

public class Calculator {
    private static double read_arg(String msg) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.print(msg);
            String n = myObj.nextLine();
            try {
                return Double.parseDouble(n);
            } catch (Exception e) {
                System.out.println("Introduceti un numar intreg sau fractionar");
            }
        }
    }

    public static int read_op(String msg) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String op = "+-*/^";
        while (true) {
            System.out.print(msg);
            System.out.print(" operation: ");
            String n = myObj.nextLine();
            int ix = op.indexOf(n);
            if (ix >= 0) return ix;
        }

    }

    public static void main(String[] args) {
        double n1 = read_arg("Introduceti primul argument a=");
        int op = read_op("Introduceti operatia +,-,*,/,^");
        double n2 = read_arg("Introduceti al doilea argument b=");
        switch (op) {
            case 0 -> System.out.println(n1 + "+" + n2 + "=" + (n1 + n2));
            case 1 -> System.out.println(n1 + "-" + n2 + "=" + (n1 - n2));
            case 2 -> System.out.println(n1 + "*" + n2 + "=" + (n1 * n2));
            case 3 -> {
                if (n2 != 0) System.out.println(n1 + "/" + n2 + "=" + (n1 / n2));
                else System.out.println("Erroare impartire la zero");
            }
            case 4 -> System.out.println(n1 + "^" + n2 + "=" + Math.pow(n1, n2));
        }

    }
}
