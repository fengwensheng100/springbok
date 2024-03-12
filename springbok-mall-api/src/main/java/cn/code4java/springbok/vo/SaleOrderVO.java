package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.entity.SaleOrderLine;
import lombok.Data;
import java.util.List;

/**
 * @ClassName SaleOrderVO
 * @Description: SaleOrderVO
 * @Author fengwensheng
 * @Date 2024/1/31
 * @Version V1.0
 **/
@Data
public class SaleOrderVO extends SaleOrder {
    /**
     * 订单明细
     */
    private List<SaleOrderLine> saleOrderLines;
}
