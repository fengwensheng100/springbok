package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName SaleOrderLogistic
 * @Description: 物流信息
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_sale_order_logistic")
public class SaleOrderLogistic extends BaseEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer saleOrderLogisticId;
    /**
     * 订单id
     */
    private Integer saleOrderId;
    /**
     * 物流公司名称
     */
    private String companyName;
    /**
     * 物流单号
     */
    private String logisticNumber;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 商品件数
     */
    private Integer count;
}
