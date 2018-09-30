package utils;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParseUtils {
    public static Map<String,String> parseQueryString(String queryString) {
        if (StringUtils.isBlank(queryString)) {
            return null;
        }
        Map<String,String> map = new HashMap<String, String>();
        try {
            String[] array_1 = queryString.split("&");
            if (null == array_1 || array_1.length == 0) {
                return null;
            }
            for (String element:array_1) {
                if (StringUtils.isBlank(element)) {
                    continue;
                }
                String[] array_2 = element.split("=");
                if (null == array_2 || array_2.length != 2) {
                    continue;
                }
                map.put(array_2[0], array_2[1]);
            }
            return map;
        }catch (Exception e) {
            LogUtils.error("Exception格式转化出错");
        }
        return null;
    }


    /**
     * 拼接请求参数（会对参数进行URLEncode）
     * @param params
     * @return
     */
    public static String getQueryString(Map<String,String> params){
        if (null == params || params.size() == 0){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int index = 0;

        for (String key:params.keySet()) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            try {
                value = URLEncoder.encode(value, "UTF-8");
            }catch (Exception e) {
                LogUtils.error("encrypt_value_error", e);
            }
            value = (index == 0 ? "" : "&") + key + "=" + value;
            builder.append(value);
            ++index;
        }
        return builder.toString();
    }


    /**
     * 拼接请求参数(不进行URLEncode)
     * @param params
     * @return   a=1&b=2&c=3
     */
    public static String getParamsForm(Map<String,String> params){
        if (null == params || params.size() == 0){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String key = "";
        for(Iterator<String> it = params.keySet().iterator(); it.hasNext(); ){
            key = it.next();
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        if(sb.indexOf("&") > -1){
            sb.deleteCharAt(sb.length() - 1);
        }

        if (StringUtils.isBlank(sb)){
            return null;
        }

        return sb.toString();
    }
}
