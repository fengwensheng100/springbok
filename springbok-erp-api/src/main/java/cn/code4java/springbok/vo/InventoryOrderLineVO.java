package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.InventoryOrderLine;
import lombok.Data;

/**
 * @ClassName InventoryOrderLineVO
 * @Description: InventoryOrderLineVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class InventoryOrderLineVO extends InventoryOrderLine {
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;
}
