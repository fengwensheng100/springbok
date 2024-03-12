package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @ClassName InventoryOrderLine
 * @Description: 盘点单明细
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_inventory_order_line")
public class InventoryOrderLine {

    /**
     * 采购出库单id
     */
    @TableId(type = IdType.AUTO)
    private Integer inventoryOrderLineId;
    /**
     * 采购入库单id
     */
    private Integer inventoryOrderId;
    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 实盘数量
     */
    private BigDecimal realQuantity;
}
