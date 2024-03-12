package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.StockLine;
import lombok.Data;

/**
 * @ClassName StockLineQueryDTO
 * @Description: StockLineQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class StockLineQueryDTO extends StockLine {

    private long size;
    private long current;

    /**
     * 商品名称
     */
    private String itemName;
}
