package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.vo.ItemSaleVO;

/**
 * @ClassName ItemSaleApiService
 * @Description: 商品API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface ItemSaleApiService {
    /**
     * 分页查询商品
     *
     * @param itemSaleQueryDTO
     * @return
     */
    Page<ItemSale> pageItemSale(ItemSaleQueryDTO itemSaleQueryDTO);

    /**
     * 根据id查询商品
     *
     * @param itemSaleId
     * @return
     * @throws InterruptedException
     */
    ItemSaleVO selectItemSaleById(int itemSaleId);
}
