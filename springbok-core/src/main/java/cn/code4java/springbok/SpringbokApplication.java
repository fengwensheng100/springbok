package cn.code4java.springbok;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SpringbokApplication
 * @Description: 启动入口类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@SpringBootApplication
@MapperScan("cn.code4java.springbok.mapper")
public class SpringbokApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbokApplication.class, args);
    }
}
