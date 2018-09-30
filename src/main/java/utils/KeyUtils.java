package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyUtils {
    private static final String  FILE_URl = "/home/encrypt/secretkey";
    public static SecretKey doSecretKey(String curid, String upCompany) {
        String outputPath = FILE_URl+"/"+upCompany+"/";
        File file = new File(outputPath);
        if (!file.exists()){
            file.mkdir();
        }

        String osname = System.getProperty("os.name");
        String privateKey = outputPath + "/" + curid + ".pfx";
        String publicKey = outputPath + "/" + curid + ".cer";
        String pkcs8Privatekey = outputPath + "/pkcs8" + curid + ".pfx";

        //获取openssl的安装路径
        String exepath = null;
        if (StringUtils.contains(osname, "Windows")) {
            //windows下openssl的安装路径;例如：D:/hanyh/tools/openssl/bin/openssl.exe
            exepath = "D:/hanyh/tools/openssl/bin/openssl.exe";
        } else {
            //linux下openssl的安装路径
            exepath = "/usr/bin/openssl";
        }

        doProcess(exepath + " genrsa -out " + privateKey + " 1024");
        doProcess(exepath + " rsa -in " + privateKey + " -pubout -out " + publicKey);
        doProcess(exepath + " pkcs8 -topk8 -inform PEM -outform DER -in " + privateKey + " -out " + pkcs8Privatekey
                    + " -nocrypt");
        return getSecretKey(upCompany, publicKey, pkcs8Privatekey);
        }



    public static SecretKey getSecretKey(String upCompany, String publicKeyPath, String pkcs8PrivatekeyPath) {
        SecretKey secretKey = new SecretKey();
        try {
            FileUtils.readFileToString(new File(pkcs8PrivatekeyPath), "utf-8");
            String publicKeyString = FileUtils.readFileToString(new File(publicKeyPath), "utf-8");
            publicKeyString = StringUtils.replace(publicKeyString, "-----BEGIN PUBLIC KEY-----", "");
            publicKeyString = StringUtils.replace(publicKeyString, "-----END PUBLIC KEY-----", "");
            secretKey.setPublicKey(publicKeyString);
            byte[] b = FileUtils.readFileToByteArray(new File(pkcs8PrivatekeyPath));
            secretKey.setPrivateKey(Base64.encodeBase64String(b));
        } catch (IOException e) {
            LogUtils.error("generate_privateKey_and_publicKey_is_error upcompany="+upCompany);
        }
        return secretKey;
    }



    private static boolean doProcess(String command) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
            if (p.exitValue() == 0) {
                System.out.println("程序运行正常");
                return true;
            }
        } catch (IOException e) {
            LogUtils.error("call_openssl_process_is_IOException", e);
        } catch (InterruptedException e) {
            LogUtils.error("call_openssl_process_is_InterruptedException", e);
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(br);
        }
        return false;
    }


    /**
     * 判断该商户号是否已生成公私钥
     * @param upcompany
     * @param curid
     * @return
     */
    public static String isExistKey(String upcompany,String curid){
        String outputPath = FILE_URl+"/"+upcompany+"/";
        File file = new File(outputPath);
        if (!file.exists()){
            return null;
        }

        String fileName = outputPath+curid+".pfx";
        File publicFile = new File(fileName);
        if (!publicFile.exists()){
            return null;
        }

        String privateKeyName = outputPath+curid+".cer";
        File privateFile = new File(privateKeyName);
        if (!privateFile.exists()){
            return null;
        }

        return outputPath;
    }
}
