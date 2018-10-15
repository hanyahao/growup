package json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class AliJson {
    public static void main(String[] args) {
         String jsonStr = "{\"sites\":[{\"name\":\"蚂蚁课堂\",\"url\":\"www.itmayiedu.com\"},{\"name\":\"每特教育\",\"url\":\"http://meiteedu.com/\"}]}";
        JSONObject json = new JSONObject();
        JSONObject jsonObject = json.parseObject(jsonStr);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("sites"));
        JSONArray jsonArray = jsonObject.getJSONArray("sites");
        System.out.println(jsonArray);
        for (Object object : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) object;
            String name = jsonObject1.getString("name");
            String url = jsonObject1.getString("url");
            System.out.println(name+" "+url);

        }

    }
}
