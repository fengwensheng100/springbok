package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.ItemSaleDTO;
import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.service.ItemSaleService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ItemSaleController
 * @Description: 商品控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "商品管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/itemSale")
public class ItemSaleController {

    private final ItemSaleService itemSaleService;

    /**
     * 分页查询商品
     *
     * @param params
     * @return
     */
    @GetMapping("/pageItemSale")
    @Operation(summary = "分页查询商品", description = "分页查询商品")
    public BaseResponse pageItemSale(ItemSaleQueryDTO params) {
        return BaseResponse.success(itemSaleService.pageItemSale(params));
    }

    /**
     * 查询商品列表
     *
     * @param itemSale
     * @return
     */
    @GetMapping("/listItemSale")
    @Operation(summary = "查询商品列表", description = "查询商品列表")
    public BaseResponse listItemSale(ItemSale itemSale) {
        return BaseResponse.success(itemSaleService.listItemSale(itemSale));
    }

    /**
     * 根据id查询商品信息
     *
     * @param itemSale
     * @return
     */
    @GetMapping("/selectItemSaleById")
    @Operation(summary = "根据id查询商品信息", description = "根据id查询商品信息")
    public BaseResponse selectItemSaleById(ItemSale itemSale) {
        return BaseResponse.success(itemSaleService.selectItemSaleById(itemSale.getItemSaleId()));
    }

    /**
     * 新增商品
     *
     * @param itemSaleDTO
     * @return
     */
    @PostMapping("/addItemSale")
    @Operation(summary = "新增商品", description = "新增商品")
    public BaseResponse addItemSale(@RequestBody ItemSaleDTO itemSaleDTO) {
        return BaseResponse.success(itemSaleService.addItemSale(itemSaleDTO));
    }

    /**
     * 修改商品
     *
     * @param itemSaleDTO
     * @return
     */
    @PostMapping("/updateItemSale")
    @Operation(summary = "修改商品", description = "修改商品")
    public BaseResponse updateItemSale(@RequestBody ItemSaleDTO itemSaleDTO) {
        return BaseResponse.success(itemSaleService.updateItemSale(itemSaleDTO));
    }

    /**
     * 删除商品
     *
     * @param itemSaleId
     * @return
     */
    @PostMapping("/deleteItemSale")
    @Operation(summary = "删除商品", description = "删除商品")
    public BaseResponse deleteItemSale(Integer itemSaleId) {
        return BaseResponse.success(itemSaleService.deleteItemSale(itemSaleId));
    }
}
