package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PurchaseInOrder
 * @Description: 采购入库单
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_purchase_in_order")
public class PurchaseInOrder extends BaseEntity {

    /**
     * 采购入库单id
     */
    @TableId(type = IdType.AUTO)
    private Integer purchaseInOrderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单总价
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态
     * 1：制单
     * 2：审核
     */
    private Integer orderStatus;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 备注
     */
    private String remark;
}
