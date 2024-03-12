package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.PurchaseInOrderLine;
import lombok.Data;

/**
 * @ClassName PurchaseInOrderLineVO
 * @Description: PurchaseInOrderLineVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class PurchaseInOrderLineVO extends PurchaseInOrderLine {
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
