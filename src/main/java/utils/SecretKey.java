package utils;

public class SecretKey {
    // 私钥 java版本的私钥 ,java 读取pkcs8 格式的比较方便，所以转换成pkcs8格式的
    private String				privateKey;
    // 公钥
    private String				publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
