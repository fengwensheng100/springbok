package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.Stock;
import cn.code4java.springbok.entity.StockLine;
import lombok.Data;

import java.util.List;

/**
 * @ClassName StockVO
 * @Description: StockVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class StockVO extends Stock {

    /**
     * 商品编码
     */
    private int itemCode;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 规格
     */
    private String spec;
    /**
     * 库存资料明细列表
     */
    private List<StockLine> stockLine;
}
