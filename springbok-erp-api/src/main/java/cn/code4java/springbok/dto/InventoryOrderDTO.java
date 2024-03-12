package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.InventoryOrder;
import cn.code4java.springbok.entity.InventoryOrderLine;
import lombok.Data;

import java.util.List;

/**
 * @ClassName InventoryOrderDTO
 * @Description: InventoryOrderDTO
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
public class InventoryOrderDTO extends InventoryOrder {

    /**
     * 盘点单明细
     */
    private List<InventoryOrderLine> inventoryOrderLineList;
}
