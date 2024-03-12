package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.ItemSaleSku;
import lombok.Data;

/**
 * @ClassName ItemSaleSkuQueryDTO
 * @Description: ItemSaleSkuQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class ItemSaleSkuQueryDTO extends ItemSaleSku {

    private long size;
    private long current;
}
