package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.ItemSaleSkuQueryDTO;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.service.ItemSaleSkuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ItemSaleSkuController
 * @Description: 商品sku控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "商品sku管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/itemSaleSku")
public class ItemSaleSkuController {

    private final ItemSaleSkuService itemSaleService;

    /**
     * 分页查询商品sku
     *
     * @param params
     * @return
     */
    @GetMapping("/pageItemSaleSku")
    @Operation(summary = "分页查询商品sku", description = "分页查询商品sku")
    public BaseResponse pageItemSaleSku(ItemSaleSkuQueryDTO params) {
        return BaseResponse.success(itemSaleService.pageItemSaleSku(params));
    }
}
