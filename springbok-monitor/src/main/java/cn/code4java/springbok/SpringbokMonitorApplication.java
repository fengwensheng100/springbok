package cn.code4java.springbok;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SpringbokMonitorApplication
 * @Description: 监控服务启动类
 * @Author fengwensheng
 * @Date 2024/3/6
 * @Version V1.0
 **/
@EnableAdminServer
@SpringBootApplication
public class SpringbokMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbokMonitorApplication.class, args);
    }
}
