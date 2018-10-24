package pattern;

public class Singleton {
    private static int STATUS = 1;
    private static Singleton singleton = new Singleton();
    private Singleton() {
        System.out.println("single create singleton");
    }

    public static Singleton getInstence() {
        return singleton;
    }

    public static void main(String[] args) {
//        Singleton singleton = Singleton.getInstence();
        System.out.println(Singleton.STATUS);

    }

}
