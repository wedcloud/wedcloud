package club.wedcloud.www;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

  public static void main(String[] args) {
    Double d = 0.5;
    String s =
        "[{\"star\":0,\"max\":0.1,\"min\":0},{\"star\":2,\"max\":0.4,\"min\":0.1},{\"star\":3,\"max\":0.7,\"min\":0.4},{\"star\":4,\"max\":0.9,\"min\":0.7},{\"star\":5,\"max\":1,\"min\":0.9}]";
    JSONArray arr = JSONArray.parseArray(s);
    for (int i = 0; i < arr.size(); i++) {
      JSONObject json = arr.getJSONObject(i);
      if (d >= json.getDouble("min") && d < json.getDouble("max")) {
        System.out.println(json.getInteger("star"));
      }
    }
  }
}
