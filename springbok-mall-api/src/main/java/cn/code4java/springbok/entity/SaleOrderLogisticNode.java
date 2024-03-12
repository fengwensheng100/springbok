package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SaleOrderLogisticNode
 * @Description: 物流节点记录
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_sale_order_logistic_node")
public class SaleOrderLogisticNode {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer saleOrderLogisticNodeId;
    /**
     * 订单物流id
     */
    private Integer saleOrderLogisticId;
    /**
     * 物流信息
     */
    private String text;
    /**
     * 时间
     */
    private Date time;
}
