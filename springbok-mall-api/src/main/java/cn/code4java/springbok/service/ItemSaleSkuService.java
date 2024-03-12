package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.ItemSaleSkuQueryDTO;
import cn.code4java.springbok.vo.ItemSaleSkuVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @ClassName ItemSaleSkuService
 * @Description: 商品sku服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface ItemSaleSkuService {
    /**
     * 分页查询商品sku
     *
     * @param itemSaleSkuQueryDTO
     * @return
     */
    Page<ItemSaleSkuVO> pageItemSaleSku(ItemSaleSkuQueryDTO itemSaleSkuQueryDTO);
}
