package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.ItemSaleClass;
import lombok.Data;

/**
 * @ClassName ItemSaleClassQueryDTO
 * @Description: ItemSaleClassQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class ItemSaleClassQueryDTO extends ItemSaleClass {

    private long size;
    private long current;
}
