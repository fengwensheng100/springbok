package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.PurchaseInOrder;
import lombok.Data;

/**
 * @ClassName PurchaseInOrderQueryDTO
 * @Description: PurchaseInOrderQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class PurchaseInOrderQueryDTO extends PurchaseInOrder {

    private long size;
    private long current;

    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 门店名称
     */
    private String branchName;
}
