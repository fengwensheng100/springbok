package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.ItemSaleDTO;
import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.vo.ItemSaleVO;

import java.util.List;

/**
 * @ClassName ItemSaleService
 * @Description: 商品服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface ItemSaleService {
    /**
     * 分页查询商品
     *
     * @param itemSaleQueryDTO
     * @return
     */
    Page<ItemSale> pageItemSale(ItemSaleQueryDTO itemSaleQueryDTO);

    /**
     * 查询商品列表
     *
     * @param itemSale
     * @return
     */
    List<ItemSale> listItemSale(ItemSale itemSale);

    /**
     * 根据id查询商品
     *
     * @param itemSaleId
     * @return
     */
    ItemSaleVO selectItemSaleById(int itemSaleId);

    /**
     * 新增商品
     *
     * @param itemSaleDTO
     * @return
     */
    int addItemSale(ItemSaleDTO itemSaleDTO);

    /**
     * 修改商品
     *
     * @param itemSaleDTO
     * @return
     */
    int updateItemSale(ItemSaleDTO itemSaleDTO);

    /**
     * 删除商品
     *
     * @param itemSaleId
     * @return
     */
    int deleteItemSale(int itemSaleId);
}
