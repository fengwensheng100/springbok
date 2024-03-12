package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @ClassName PurchaseOutOrderLine
 * @Description: 采购出库单明细
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_purchase_out_order_line")
public class PurchaseOutOrderLine {

    /**
     * 采购出库单id
     */
    @TableId(type = IdType.AUTO)
    private Integer purchaseOutOrderLineId;
    /**
     * 采购出库单id
     */
    private Integer purchaseOutOrderId;
    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 总价
     */
    private BigDecimal amount;
}
