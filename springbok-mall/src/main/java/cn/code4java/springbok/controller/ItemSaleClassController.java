package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.ItemSaleClassQueryDTO;
import cn.code4java.springbok.entity.ItemSaleClass;
import cn.code4java.springbok.service.ItemSaleClassService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ItemSaleClassController
 * @Description: 商品分类控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "商品分类管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/itemSaleClass")
public class ItemSaleClassController {

    private final ItemSaleClassService itemSaleClassService;

    /**
     * 分页查询商品分类
     *
     * @param params
     * @return
     */
    @GetMapping("/pageItemSaleClass")
    @Operation(summary = "分页查询商品分类", description = "分页查询商品分类")
    public BaseResponse pageItemSaleClass(ItemSaleClassQueryDTO params) {
        return BaseResponse.success(itemSaleClassService.pageItemSaleClass(params));
    }

    /**
     * 查询商品分类列表
     *
     * @param itemSaleClass
     * @return
     */
    @GetMapping("/listItemSaleClass")
    @Operation(summary = "查询商品分类列表", description = "查询商品分类列表")
    public BaseResponse listItemSaleClass(ItemSaleClass itemSaleClass) {
        return BaseResponse.success(itemSaleClassService.listItemSaleClass(itemSaleClass));
    }

    /**
     * 新增商品分类
     *
     * @param itemSaleClass
     * @return
     */
    @PostMapping("/addItemSaleClass")
    @Operation(summary = "新增商品分类", description = "新增商品分类")
    public BaseResponse addItemSaleClass(@RequestBody ItemSaleClass itemSaleClass) {
        return BaseResponse.success(itemSaleClassService.addItemSaleClass(itemSaleClass));
    }

    /**
     * 修改商品分类
     *
     * @param itemSaleClass
     * @return
     */
    @PostMapping("/updateItemSaleClass")
    @Operation(summary = "修改商品分类", description = "修改商品分类")
    public BaseResponse updateItemSaleClass(@RequestBody ItemSaleClass itemSaleClass) {
        return BaseResponse.success(itemSaleClassService.updateItemSaleClass(itemSaleClass));
    }

    /**
     * 删除商品分类
     *
     * @param itemSaleClassId
     * @return
     */
    @PostMapping("/deleteItemSaleClass")
    @Operation(summary = "删除商品分类", description = "删除商品分类")
    public BaseResponse deleteItemSaleClass(Integer itemSaleClassId) {
        return BaseResponse.success(itemSaleClassService.deleteItemSaleClass(itemSaleClassId));
    }
}
