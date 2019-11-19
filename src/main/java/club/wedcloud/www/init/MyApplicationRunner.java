package club.wedcloud.www.init;

import club.wedcloud.www.service.impl.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Slf4j
@Component
/**
 * 不同于 CommandLineRunner 中的 run 方法的String 数组参数，这里 run 方法的参数是一个 ApplicationArguments 对象，
 * 如果想从 ApplicationArguments 对象中获取入口类中 main 方法接受的参数，调用 ApplicationArguments 中的 getNonOptionArgs 方法即可。
 * ApplicationArguments 中的 getNonOptionNames 方法用来获取项目启动命令行中参数的 key ，
 * 例如将本项目打成 jar 包 ，运行 java -jar xxx.jar -name=Michael 命令来启动项目，此时 getOptionNames 方法获取到的就是 name,而 getOptionValues 方法则是获取相应的 value
 */
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private AlbumService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("MyApplicationRunner >>> run,{}",service.getInfo(2));
    }
}
