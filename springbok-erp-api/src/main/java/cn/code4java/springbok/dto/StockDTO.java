package cn.code4java.springbok.dto;

import lombok.Data;

/**
 * @ClassName StockDTO
 * @Description: StockDTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class StockDTO {

    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 门店名称
     */
    private String branchName;
}
