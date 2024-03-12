package cn.code4java.springbok.constant;

/**
 * @ClassName MQConstant
 * @Description: MQ常量类
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public interface MQConstant {

    //------------------ Exchange ------------------
    /**
     * direct交换机
     */
    String EX_MALL_DIRECT = "ex.mall.direct";

    //------------------ Queue ------------------
    /**
     * 统计队列
     */
    String Q_MALL_STATISTICS = "queue.mall.statistics";

    //------------------ Key ------------------
    /**
     * 统计队列路由key
     */
    String KEY_STATISTICS = "statistics";

}
