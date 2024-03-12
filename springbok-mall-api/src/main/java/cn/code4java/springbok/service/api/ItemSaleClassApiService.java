package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.vo.api.ItemSaleClassApiVO;

import java.util.List;

/**
 * @ClassName ItemSaleClassApiService
 * @Description: 商品分类API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface ItemSaleClassApiService {
    /**
     * 查询商品分类列表
     *
     * @param itemSaleClassQueryDTO
     * @return
     */
    List<ItemSaleClass> listItemSaleClass(ItemSaleClassQueryDTO itemSaleClassQueryDTO);

    /**
     * 根据id查询商品分类
     *
     * @param itemSaleClassId
     * @return
     */
    ItemSaleClassApiVO selectItemSaleClassById(int itemSaleClassId);
}
