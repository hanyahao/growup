package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;


public class RSAUtils {
    /**
     * RSA签名      SHA1withRSA
     * @param content 待签名的内容
     * @param privateKey 私钥
     * @return 签名结果
     * */
    public static byte[] sign(String content,PrivateKey privateKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (null == privateKey) {
            return null;
        }
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes("utf-8"));
            byte [] result = signature.sign();
            return result;
        }catch (Exception e) {
            LogUtils.error("RSA签名错误",e);
        }
        return null;
    }
	/**
	 * RSA签名      SHA1withRSA
	 * @param bytes 待签名的内容
	 * @param privateKey 私钥
	 * @return 签名结果
	 * */
	public static byte[] sign(byte[] bytes, PrivateKey privateKey) {
		if (bytes == null) {
			return null;
		}
		if (null == privateKey) {
			return null;
		}
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(privateKey);
			signature.update(bytes);
			return signature.sign();
		}catch (Exception e) {
			LogUtils.error("RSA签名错误",e);
		}
		return null;
	}
	

    /**
     * RSA签名      SHA256withRSA
     * @param content 待签名的内容
     * @param privateKey 私钥
     * @return 签名结果
     * */
    public static byte[] sign256(String content,PrivateKey privateKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (null == privateKey) {
            return null;
        }
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes("utf-8"));
            byte [] result = signature.sign();
            return result;
        }catch (Exception e) {
            LogUtils.error("RSA签名错误",e);
        }
        return null;
    }


    /**
     * RSA签名           MD5withRSA
     * @param content
     * @param privateKey
     * @return
     */
    public  static String MD5withRSASign(String content,PrivateKey privateKey){
        if (StringUtils.isBlank(content)) {
            return null;
        }
        if (null == privateKey) {
            return null;
        }

        Signature signature = null;
        try {
            signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes("utf-8"));
            byte [] result = signature.sign();
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
           throw new RuntimeException("MD5withRSA_生成Sign出错");
        }


    }

    /**
     * 签名验证
     * @param sign 签名
     * @param content 待验证签名的原文
     * @param publicKey 公钥
     * @return 签名验证结果
     * */
    public static boolean verifySign(byte[] sign, String content,PublicKey publicKey) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        if (null == publicKey || null == sign) {
            return false;
        }
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(content.getBytes("utf-8"));
            return signature.verify(sign);
        }catch (Exception e) {
            LogUtils.error("RSA签名校验错误",e);
        }
        return false;
    }
	
	
	/**
	 * 签名验证
	 * @param sign 签名
	 * @param content 待验证签名的原文
	 * @param publicKey 公钥
	 * @return 签名验证结果
	 * */
	public static boolean verifySign(byte[] sign, byte[] content,PublicKey publicKey) {
		if (null == content) {
			return false;
		}
		if (null == publicKey || null == sign) {
			return false;
		}
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(publicKey);
			signature.update(content);
			return signature.verify(sign);
		}catch (Exception e) {
			LogUtils.error("RSA签名校验错误",e);
		}
		return false;
	}
	
	

    /**
     * 签名验证 && SHA256withRSA
     * @param sign 签名
     * @param content 待验证签名的原文
     * @param publicKey 公钥
     * @return 签名验证结果
     * */
    public static boolean verifySignWithSHA256(byte[] sign, String content,PublicKey publicKey) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        if (null == publicKey || null == sign) {
            return false;
        }
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(content.getBytes("utf-8"));
            return signature.verify(sign);
        }catch (Exception e) {
            LogUtils.error("RSA签名校验错误",e);
        }
        return false;
    }

    /**
     * 获取RSA私钥
     * @param content 私钥内容
     * @return 私钥对象
     * */
    public static PrivateKey privateKey(byte[] content) {
        if (null == content) {
            return null;
        }
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
            return factory.generatePrivate(privateKeySpec);
        }catch (Exception e) {
            LogUtils.error("RSA获取私钥错误",e);
        }
        return null;
    }

    /**
     * 获取RSA公钥
     * @param content 公钥内容
     * @return 公钥对象
     * */
    public static PublicKey publicKey(byte[] content) {
        if (null == content) {
            return null;
        }
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(content);
            return factory.generatePublic(publicKeySpec);
        }catch (Exception e) {
            LogUtils.error("get_RSA_public_key_error",e);
        }
        return null;
    }

    public static String encrypt(String content,PublicKey publicKey, int maxLength) throws Exception {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        byte[] data = content.getBytes("UTF-8");
        byte[] encryptResult = encrypt(data, publicKey, maxLength);
        return Base64.encodeBase64String(encryptResult);
    }

    public static String decrypt(String content, PrivateKey privateKey, int maxLength) throws Exception {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        byte[] data = Base64.decodeBase64(content);
        byte[] decryptData = decrypt(data, privateKey, maxLength);
        return new String(decryptData);
    }

    public static String decrypt(String content, PrivateKey privateKey, int maxLength, String charset) throws Exception {
        if (StringUtils.isBlank(content)
                || StringUtils.isBlank(charset)) {
            return null;
        }
        byte[] data = Base64.decodeBase64(content);
        byte[] decryptData = decrypt(data, privateKey, maxLength);
        return new String(decryptData, charset);
    }

    public static byte[] decrypt(byte[] content, PrivateKey privateKey, int maxLength) throws Exception {
        if (null == content
                || null == privateKey
                || 0 == maxLength) {
            return null;
        }
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int offset = 0;
        int contentLength = content.length;
        for (int i = 0; contentLength - offset > 0 ; offset = i * maxLength) {
            byte[] decryptCache;
            int length = contentLength - offset;
            if (length > maxLength) {
                decryptCache = cipher.doFinal(content, offset, maxLength);
            }else {
                decryptCache = cipher.doFinal(content, offset, length);
            }
            outputStream.write(decryptCache, 0, decryptCache.length);
            i++;
        }
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }
    public static byte[] encrypt(byte[] content, PublicKey publicKey, int maxLength) throws Exception{
        if (null == content
                || null == publicKey
                || maxLength == 0) {
            return null;
        }
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int offset = 0;
        int length = content.length;
        for (int i = 0; length - offset > 0 ; offset = i * maxLength) {
            byte[] cache;
            if (length - offset > maxLength) {
                cache = cipher.doFinal(content, offset, maxLength);
            }else {
                cache = cipher.doFinal(content, offset, length - offset);
            }
            outputStream.write(cache, 0, cache.length);
            i++;
        }
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }

    public static byte[] encrypt(byte[] content, PublicKey key) {
        if (null == content
                || null == key) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipher.update(content);
            return cipher.doFinal();
        }catch (Exception e) {
            LogUtils.error("rsa_encrypt_error", e);
        }
        return null;
    }

    public static String encrypt(String content,PublicKey key) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            byte[] result = encrypt(content.getBytes("UTF-8"), key);
            return  Base64.encodeBase64String(result);
        }catch (Exception e) {
            LogUtils.error("rsa_encrypt_error_byte", e);
        }
        return null;
    }


    /**
     * 根据RSA证书路径获取公钥
     * @param credentialPath   公钥证书路径
     * @return                 公钥对象PublicKey
     *                          证书格式  X.509
     * @throws Exception
     */
    public static PublicKey getPublicKeyByCredential(String credentialPath)  {
        if (StringUtils.isBlank(credentialPath)) {
            return null;
        }

        InputStream in = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            in = new FileInputStream(new File(credentialPath));
            Certificate certificate = cf.generateCertificate(in);
            PublicKey key = certificate.getPublicKey();
            if (null != key) {
                return key;
            }
        } catch (CertificateException e) {
            LogUtils.error("CertificateException读取公钥出错",e);
        } catch (FileNotFoundException e) {
            LogUtils.error("FileNotFoundException读取公钥出错",e);
        }finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                LogUtils.error("IOException流关闭出错",e);
            }
        }
        return null;
    }


    /**
     * 根据RSA证书地址和证书密码获取私钥
     * @param rsaCredentialPath      私钥路径
     * @param password               私钥密码
     * @return                       私钥对象PrivateKey
     *                                签名算法  PKCS12
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByRsaCredential(String rsaCredentialPath,String password){
        if (StringUtils.isBlank(rsaCredentialPath) || StringUtils.isBlank(password)) {
            return null;
        }

        InputStream in = null;
        try {
            in = new FileInputStream(new File(rsaCredentialPath));
            KeyStore keyStoreCA = KeyStore.getInstance("PKCS12");
            keyStoreCA.load(in,password.toCharArray());
            Enumeration<String> aliases = keyStoreCA.aliases();
            if (null == aliases) {
                return null;
            }

            String keyAliase = "";
            while (aliases.hasMoreElements()) {
                keyAliase = aliases.nextElement();
                PrivateKey key = (PrivateKey) (keyStoreCA.getKey(keyAliase,password.toCharArray()));
                if (null != key) {
                    return key;
                }
            }
        } catch (FileNotFoundException e) {
            LogUtils.error("读取文件错误",e);
        } catch (CertificateException e) {
            LogUtils.error("CertificateException读取私钥出错",e);
        } catch (IOException e) {
            LogUtils.error("IOException读取私钥出错",e);
        }catch (NoSuchAlgorithmException e) {
            LogUtils.error("NoSuchAlgorithmException读取私钥出错",e);
        } catch (KeyStoreException e) {
            LogUtils.error("KeyStoreException读取私钥出错",e);
        }catch (UnrecoverableKeyException e) {
            LogUtils.error("UnrecoverableKeyException读取私钥出错",e);
        }finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    LogUtils.error("IOException流关闭出错",e);
                }
            }
        }
        return null;
    }

}
