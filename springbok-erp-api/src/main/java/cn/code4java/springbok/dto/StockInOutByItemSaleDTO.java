package cn.code4java.springbok.dto;

import cn.code4java.springbok.dto.api.ItemSaleSkuApiDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName StockInOutByItemSaleDTO
 * @Description: 库存出入库DTO
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Data
public class StockInOutByItemSaleDTO {
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 销售商品规格列表
     */
    private List<ItemSaleSkuApiDTO> itemSaleSkuList;
    /**
     * 订单类型
     */
    private int billType;
}
