package cn.code4java.springbok.vo.api;

import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.entity.SaleOrderLine;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SaleOrderApiVO
 * @Description: 预订单VO
 * @Author fengwensheng
 * @Date 2024/1/31
 * @Version V1.0
 **/
@Data
public class SaleOrderApiVO extends SaleOrder {
    /**
     * 商品集合
     */
    private List<SaleOrderLine> saleOrderLines;
    /**
     * 倒计时--剩余的秒数 负数表示已经超时，正数表示倒计时未结束
     */
    private Long countdown;
}
