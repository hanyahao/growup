package proxy;

public class SHA1Strategy implements Strategy {
    @Override
    public void encrypt() {
        System.out.println("执行SHA1加密");
    }
}
