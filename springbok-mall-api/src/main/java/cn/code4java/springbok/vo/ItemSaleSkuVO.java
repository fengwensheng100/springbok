package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.ItemSaleSku;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ItemSaleSkuVO
 * @Description: ItemSaleSkuVO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class ItemSaleSkuVO extends ItemSaleSku {

    /**
     * 库存
     */
    private BigDecimal quantity;
}
