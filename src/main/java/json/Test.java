package json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {
        JSONObject root = new JSONObject();
        root.put("id", "01");
        root.put("name", "张三");
        JSONArray arrayList = new JSONArray();
        JSONObject object1 = new JSONObject();
        object1.put("itemId", "20");
        object1.put("itemName", "蚂蚁课堂");
        JSONObject object2 = new JSONObject();
        object2.put("itemId", "30");
        object2.put("itemName", "每特学院");
        arrayList.add(object1);
        arrayList.add(object2);
        root.put("items", arrayList);
        System.out.println(root.toJSONString());
        User user = new JSONObject().parseObject(root.toJSONString(),User.class);
        System.out.println(user.toString());
        System.out.println(new JSONObject().toJSONString(user));

    }
}
