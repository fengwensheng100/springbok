package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.entity.ItemSaleSku;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ItemSaleDTO
 * @Description: ItemSaleDTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class ItemSaleDTO extends ItemSale {

    /**
     * 销售商品规格列表
     */
    private List<ItemSaleSku> itemSaleSkuList;
    /**
     * 标签列表
     */
    private List<Integer> tagIdList;
}
