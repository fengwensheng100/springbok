package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.PurchaseOutOrder;
import cn.code4java.springbok.entity.PurchaseOutOrderLine;
import lombok.Data;
import java.util.List;

/**
 * @ClassName PurchaseOutOrderDTO
 * @Description: PurchaseOutOrderDTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class PurchaseOutOrderDTO extends PurchaseOutOrder {

    /**
     * 采购出库单明细
     */
    private List<PurchaseOutOrderLine> purchaseOutOrderLineList;
}
