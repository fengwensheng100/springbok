package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.vo.ItemSaleClassVO;

import java.util.List;

/**
 * @ClassName ItemSaleClassService
 * @Description: 商品分类服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface ItemSaleClassService extends IService<ItemSaleClass> {
    /**
     * 分页查询商品分类
     *
     * @param itemSaleClassQueryDTO
     * @return
     */
    Page<ItemSaleClassVO> pageItemSaleClass(ItemSaleClassQueryDTO itemSaleClassQueryDTO);

    /**
     * 查询商品分类列表
     *
     * @param itemSaleClass
     * @return
     */
    List<ItemSaleClass> listItemSaleClass(ItemSaleClass itemSaleClass);

    /**
     * 构建商品分类树
     *
     * @param itemSaleClassVO
     * @param itemSaleClassVOS
     */
    void buildItemSaleClassTree(ItemSaleClassVO itemSaleClassVO, List<ItemSaleClassVO> itemSaleClassVOS);

    /**
     * 新增商品分类
     *
     * @param itemSaleClass
     * @return
     */
    int addItemSaleClass(ItemSaleClass itemSaleClass);

    /**
     * 修改商品分类
     *
     * @param itemSaleClass
     * @return
     */
    int updateItemSaleClass(ItemSaleClass itemSaleClass);

    /**
     * 删除商品分类
     *
     * @param itemSaleClassId
     * @return
     */
    int deleteItemSaleClass(int itemSaleClassId);
}
