package proxy;

public class MD5Strategy implements Strategy {
    @Override
    public void encrypt() {
        System.out.println("执行MD5加密");
    }
}
