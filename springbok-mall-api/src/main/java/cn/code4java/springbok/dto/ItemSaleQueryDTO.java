package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.ItemSale;
import lombok.Data;

/**
 * @ClassName ItemSaleQueryDTO
 * @Description: ItemSaleQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class ItemSaleQueryDTO extends ItemSale {

    private long size;
    private long current;
}
