package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.PurchaseInOrder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PurchaseInOrderVO
 * @Description: PurchaseInOrderVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class PurchaseInOrderVO extends PurchaseInOrder {
    /**
     * 门店编码
     */
    private String branchNo;
    /**
     * 门店名称
     */
    private String branchName;

    private List<PurchaseInOrderLineVO> purchaseInOrderLineList;
}
