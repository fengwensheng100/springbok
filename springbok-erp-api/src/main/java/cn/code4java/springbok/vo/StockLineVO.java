package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.StockLine;
import lombok.Data;

/**
 * @ClassName StockLineVO
 * @Description: StockLineVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class StockLineVO extends StockLine {

    /**
     * 商品编码
     */
    private int itemCode;
    /**
     * 商品名称
     */
    private String itemName;
}
