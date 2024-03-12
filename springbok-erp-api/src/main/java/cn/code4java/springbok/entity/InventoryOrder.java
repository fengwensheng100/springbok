package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * @ClassName InventoryOrder
 * @Description: 盘点单
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_inventory_order")
public class InventoryOrder extends BaseEntity {

    /**
     * 盘点单id
     */
    @TableId(type = IdType.AUTO)
    private Integer inventoryOrderId;
    /**
     * 订单号
     */
    private String orderNo;
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
