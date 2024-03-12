package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName SaleOrderRefund
 * @Description: 退货订单
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_sale_order_refund")
public class SaleOrderRefund extends BaseEntity {
}
