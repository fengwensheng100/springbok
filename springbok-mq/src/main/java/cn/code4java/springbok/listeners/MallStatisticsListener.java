package cn.code4java.springbok.listeners;

import cn.code4java.springbok.constant.MQConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: fengwensheng
 * @CreateTime: 2024-02-14 23:16
 * @Description: 统计队列监听器-示例
 * @Version: 1.0
 */
@Component
public class MallStatisticsListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstant.EX_MALL_DIRECT, durable = "true"),
            exchange = @Exchange(name = MQConstant.Q_MALL_STATISTICS, type = ExchangeTypes.DIRECT),
            key = {MQConstant.KEY_STATISTICS}
    ))
    public void handleMessage(Map<String, Object> map) throws Exception {
        System.out.println("消费者1接收消息，" + map);
    }
}
