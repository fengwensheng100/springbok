package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.service.SaleOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderController
 * @Description: 订单控制器
 * @Author fengwensheng
 * @Date 2024/2/2
 * @Version V1.0
 **/
@Tag(name = "订单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/saleOrder")
public class SaleOrderController {

    private final SaleOrderService saleOrderService;

    /**
     * 分页查询销售订单
     *
     * @param saleOrderQueryDTO
     * @return
     */
    @GetMapping("/pageSaleOrder")
    @Operation(summary = "分页查询销售订单", description = "分页查询销售订单")
    public BaseResponse pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO) {
        return BaseResponse.success(saleOrderService.pageSaleOrder(saleOrderQueryDTO));
    }

    /**
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/selectSaleOrderById")
    @Operation(summary = "根据id查询订单信息", description = "根据id查询订单信息")
    public BaseResponse selectSaleOrderById(Integer id) {
        return BaseResponse.success(saleOrderService.selectSaleOrderById(id));
    }

    /**
     * 订单发货
     *
     * @param id
     * @return
     */
    @PostMapping("/deliverSaleOrder")
    @Operation(summary = "订单发货", description = "订单发货")
    public BaseResponse deliverSaleOrder(Integer id) {
        return BaseResponse.success(saleOrderService.deliverSaleOrder(id));
    }
}
