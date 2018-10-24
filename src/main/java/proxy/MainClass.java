package proxy;

public class MainClass {
    public static void main(String[] args) {
        Context context = new Context(new MD5Strategy());
        context.encrypt();
    }
}
