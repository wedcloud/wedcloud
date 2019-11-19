package club.wedcloud.www;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WwwApplicationTests {

    @Autowired
    private AipBodyAnalysis client;

    @Test
    public void contextLoads() {
        Long time = System.currentTimeMillis();
        // 调用接口
        String path = "D:\\Users\\User\\Desktop\\images\\1_S_-491819432.jpg";
        JSONObject res = client.bodyAnalysis(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        System.out.println(System.currentTimeMillis() - time);
    }

}
