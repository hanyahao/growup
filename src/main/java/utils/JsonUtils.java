package utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JsonUtils {
    public static Map<String, String> getMap(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }

        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            if (null == jsonObject) {
                return null;
            }

            Map<String, String> paramsMap = new HashMap<String, String>();
            for (Entry<String, Object> data : jsonObject.entrySet()) {
                String key = data.getKey();
                String value = String.valueOf(data.getValue());
                paramsMap.put(key, value);
            }

            return paramsMap;
        } catch (Exception e) {
            LogUtils.error("json to map is exception");
        }
        return null;
    }


    public static String getJsonStringFromMap(Map<String, String> paramsMap) {
        if (null == paramsMap) {
            return null;
        }

        try {
            return JSONObject.toJSONString(paramsMap);
        } catch (Exception e) {

        }

        return null;
    }


    /**
     * java对象转换成json
     *
     * @param object 实体对象
     * @return
     */
    public static String getJsonStringFromObject(Object object) {
        if (null == object) {
            return null;
        }

        try {
            return JSONObject.toJSONString(object);
        } catch (Exception e) {

        }

        return null;
    }




}