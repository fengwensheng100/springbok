package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.PurchaseOutOrder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PurchaseOutOrderVO
 * @Description: PurchaseOutOrderVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class PurchaseOutOrderVO extends PurchaseOutOrder {
    /**
     * 门店编码
     */
    private String branchNo;
    /**
     * 门店名称
     */
    private String branchName;

    private List<PurchaseOutOrderLineVO> purchaseOutOrderLineList;
}
