package cn.code4java.springbok.api;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.service.api.ItemSaleClassApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ItemSaleClassApi
 * @Description: 商品分类API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itemSaleClass")
public class ItemSaleClassApi {

    private final ItemSaleClassApiService itemSaleClassApiService;

    /**
     * 查询商品分类列表
     *
     * @param itemSaleClassQueryDTO
     * @return
     */
    @GetMapping("/listItemSaleClass")
    public BaseResponse listItemSaleClass(ItemSaleClassQueryDTO itemSaleClassQueryDTO) {
        return BaseResponse.success(itemSaleClassApiService.listItemSaleClass(itemSaleClassQueryDTO));
    }

    /**
     * 根据id查询商品分类详情
     *
     * @param itemSaleClass
     * @return
     */
    @GetMapping("/selectItemSaleClassById")
    public BaseResponse selectItemSaleById(ItemSaleClass itemSaleClass) {
        return BaseResponse.success(itemSaleClassApiService.selectItemSaleClassById(itemSaleClass.getItemSaleClassId()));
    }
}
