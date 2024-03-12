package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.ItemSale;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ItemSaleVO
 * @Description: 销售商品VO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class ItemSaleVO extends ItemSale {

    /**
     * 营销分类名称
     */
    private String itemSaleClassName;
    /**
     * 销售商品规格列表
     */
    private List<ItemSaleSkuVO> itemSaleSkuList;
}
