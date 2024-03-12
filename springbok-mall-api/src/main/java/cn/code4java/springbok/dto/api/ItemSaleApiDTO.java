package cn.code4java.springbok.dto.api;

import lombok.Data;

/**
 * @ClassName ItemSaleClassDTO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2023/11/27
 * @Version V1.0
 **/
@Data
public class ItemSaleApiDTO {

    /**
     * 销售商品编码
     */
    private int itemSaleCode;
    /**
     * 销售商品名称
     */
    private String itemSaleName;
    /**
     * 营销分类id
     */
    private int itemSaleClassId;
}
