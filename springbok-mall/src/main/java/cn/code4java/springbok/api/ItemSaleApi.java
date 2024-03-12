package cn.code4java.springbok.api;

import cn.code4java.springbok.annotation.ApiSecurity;
import cn.code4java.springbok.dto.ItemSaleQueryDTO;
import cn.code4java.springbok.entity.ItemSale;
import cn.code4java.springbok.service.api.ItemSaleApiService;
import cn.code4java.springbok.vo.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ItemSaleApi
 * @Description: 销售商品API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itemSale")
public class ItemSaleApi {

    private final ItemSaleApiService itemSaleApiService;

    /**
     * 分页查询销售商品
     *
     * @param itemSaleQueryDTO
     * @return
     */
    @GetMapping("/pageItemSale")
    public BaseResponse pageItemSale(ItemSaleQueryDTO itemSaleQueryDTO) {
        return BaseResponse.success(itemSaleApiService.pageItemSale(itemSaleQueryDTO));
    }

    /**
     * 根据id查询销售商品
     *
     * @param itemSale
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/selectItemSaleById")
    public BaseResponse selectItemSaleById(ItemSale itemSale) throws InterruptedException {
        return BaseResponse.success(itemSaleApiService.selectItemSaleById(itemSale.getItemSaleId()));
    }
}
