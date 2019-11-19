package club.wedcloud.www.init;

import club.wedcloud.www.service.impl.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
/**
 * @Order(1) 注解用来描述 CommanLineRunner 的执行顺序，数字越小越先执行
 * run 方法中是调用的核心逻辑，参数是系统启动时传入的参数，即入口类 main方法的参数（在调用 SpringApplication.run 方法时被传入 Springboot 项目中）
 */
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AlbumService service;

    @Override
    public void run(String... args) throws Exception {
        log.debug("MyCommandLineRunner >>> run,{}",service.getInfo(1));
    }
}
