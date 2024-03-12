package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.InventoryOrder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName InventoryOrderVO
 * @Description: InventoryOrderVO
 * @Author fengwensheng
 * @Date 2023/11/23
 * @Version V1.0
 **/
@Data
public class InventoryOrderVO extends InventoryOrder {
    /**
     * 门店编码
     */
    private String branchNo;
    /**
     * 门店名称
     */
    private String branchName;
    /**
     * 盘点单明细
     */
    private List<InventoryOrderLineVO> inventoryOrderLineList;
}
