package URL;

public class Recursion {
    public static void main(String[] args) {
        System.out.println(recursion(15));

    }

    public  static int  recursion(int n) {
        if (n == 1) {
            System.out.println("------------");
            return 1;
        }
        return n * recursion(n - 1);


    }
}
