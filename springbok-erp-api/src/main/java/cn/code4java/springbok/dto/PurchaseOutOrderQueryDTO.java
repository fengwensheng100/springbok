package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.PurchaseOutOrder;
import lombok.Data;

/**
 * @ClassName PurchaseOutOrderQueryDTO
 * @Description: PurchaseOutOrderQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class PurchaseOutOrderQueryDTO extends PurchaseOutOrder {

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
