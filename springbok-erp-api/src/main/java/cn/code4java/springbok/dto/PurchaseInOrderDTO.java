package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.PurchaseInOrder;
import cn.code4java.springbok.entity.PurchaseInOrderLine;
import lombok.Data;
import java.util.List;

/**
 * @ClassName PurchaseInOrderDTO
 * @Description: PurchaseInOrderDTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class PurchaseInOrderDTO extends PurchaseInOrder {

    /**
     * 采购入库单明细
     */
    private List<PurchaseInOrderLine> purchaseInOrderLineList;
}
