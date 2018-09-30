package utils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtils {
    public static String getSignContent(Map<String, String> map) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        int index = 0;

        for (int i = 0; i < keys.size(); ++i) {
            String key = keys.get(i);
            if (StringUtils.isBlank(key)) {
                continue;
            }
            String value = map.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            content.append((index == 0 ? "" : "&") + key + "=" + value);
            ++index;
        }
        return content.toString();
    }

    public static String md5(String content,String signKey, boolean toUpper) {
        StringBuilder builder = new StringBuilder();
        builder.append(content).append("&key=").append(signKey);
        String result = md5(builder.toString());
        if (StringUtils.isBlank(result)) {
            return null;
        }
        return toUpper ? result.toUpperCase() : result;

    }



    public static String md5(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            byte[] b = content.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuilder builder = new StringBuilder(32);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    builder.append('0');
                }
                builder.append(Integer.toString(v, 16).toLowerCase());
            }
            return builder.toString();
        } catch (Exception e) {
            LogUtils.error("sign_utils签名错误",e);
        }
        return null;
    }




}
