package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.PurchaseOutOrderDTO;
import cn.code4java.springbok.dto.PurchaseOutOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseOutOrder;
import cn.code4java.springbok.service.PurchaseOutOrderService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PurchaseOutOrderController
 * @Description: 采购出库单控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "采购出库单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/purchaseOutOrder")
public class PurchaseOutOrderController {

    private final PurchaseOutOrderService purchaseOutOrderService;

    /**
     * 分页查询采购出库单
     *
     * @param purchaseOutOrderQueryDTO
     * @return
     */
    @GetMapping("/pagePurchaseOutOrder")
    @Operation(summary = "分页查询采购出库单", description = "分页查询采购出库单")
    public BaseResponse pagePurchaseOutOrder(PurchaseOutOrderQueryDTO purchaseOutOrderQueryDTO) {
        return BaseResponse.success(purchaseOutOrderService.pagePurchaseOutOrder(purchaseOutOrderQueryDTO));
    }

    /**
     * 查询采购出库单列表
     *
     * @param purchaseOutOrder
     * @return
     */
    @GetMapping("/listPurchaseOutOrder")
    @Operation(summary = "查询采购出库单列表", description = "查询采购出库单列表")
    public BaseResponse listPurchaseOutOrder(@RequestBody PurchaseOutOrder purchaseOutOrder) {
        return BaseResponse.success(purchaseOutOrderService.listPurchaseOutOrder(purchaseOutOrder));
    }

    /**
     * 根据id查询采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    @GetMapping("/selectPurchaseOutOrderById")
    @Operation(summary = "根据id查询采购出库单", description = "根据id查询采购出库单")
    public BaseResponse selectPurchaseOutOrderById(Integer purchaseOutOrderId) {
        return BaseResponse.success(purchaseOutOrderService.selectPurchaseOutOrderById(purchaseOutOrderId));
    }

    /**
     * 新增采购出库单
     *
     * @param purchaseOutOrderDTO
     * @return
     */
    @PostMapping("/addPurchaseOutOrder")
    @Operation(summary = "新增采购出库单", description = "新增采购出库单")
    public BaseResponse addPurchaseOutOrder(@RequestBody PurchaseOutOrderDTO purchaseOutOrderDTO) {
        return BaseResponse.success(purchaseOutOrderService.addPurchaseOutOrder(purchaseOutOrderDTO));
    }

    /**
     * 修改采购出库单
     *
     * @param purchaseOutOrderDTO
     * @return
     */
    @PostMapping("/updatePurchaseOutOrder")
    @Operation(summary = "修改采购出库单", description = "修改采购出库单")
    public BaseResponse updatePurchaseOutOrder(@RequestBody PurchaseOutOrderDTO purchaseOutOrderDTO) {
        return BaseResponse.success(purchaseOutOrderService.updatePurchaseOutOrder(purchaseOutOrderDTO));
    }

    /**
     * 删除采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    @PostMapping("/deletePurchaseOutOrder")
    @Operation(summary = "删除采购出库单", description = "删除采购出库单")
    public BaseResponse deletePurchaseOutOrder(Integer purchaseOutOrderId) {
        return BaseResponse.success(purchaseOutOrderService.deletePurchaseOutOrder(purchaseOutOrderId));
    }

    /**
     * 审核采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    @PostMapping("/auditPurchaseOutOrder")
    @Operation(summary = "审核采购出库单", description = "审核采购出库单")
    public BaseResponse auditPurchaseOutOrder(Integer purchaseOutOrderId) {
        return BaseResponse.success(purchaseOutOrderService.auditPurchaseOutOrder(purchaseOutOrderId));
    }
}
