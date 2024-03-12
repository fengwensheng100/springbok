package cn.code4java.springbok.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName RabbitMQConfig
 * @Description: RabbitMQ配置类
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Configuration
public class RabbitMQConfig {

    /**
     * jackson消息转换器
     */
    @Bean
    public MessageConverter jacksonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
