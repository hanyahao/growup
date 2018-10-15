package pattern;

import java.io.Console;
import java.util.Scanner;

public class Pattern01 {
    public static void main(String[] args) {
        System.out.print("请输入数字A:");
        Scanner scanner = new Scanner(System.in);
        Integer A = Integer.parseInt(scanner.next());
        System.out.println("请选择运算符号（+，—,*,/）");
        String B = scanner.next();
        System.out.print("请输入数字C:");
        Integer C = Integer.parseInt(scanner.next());
        Integer D = 0;


        if (B.equals("+")) {
            D = A + C;
        }
        if (B.equals("-")) {
            D = A - C;
        }
        if (B.equals("*")) {
            D = A * C;
        }
        if (B.equals("/")) {
            D = A / C;
        }

        System.out.println(D);



    }
}


