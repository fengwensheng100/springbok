package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.PurchaseInOrderDTO;
import cn.code4java.springbok.dto.PurchaseInOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseInOrder;
import cn.code4java.springbok.service.PurchaseInOrderService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PurchaseInOrderController
 * @Description: 采购入库单控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "采购入库单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/purchaseInOrder")
public class PurchaseInOrderController {

    private final PurchaseInOrderService purchaseInOrderService;

    /**
     * 分页查询采购入库单
     *
     * @param purchaseInOrderQueryDTO
     * @return
     */
    @GetMapping("/pagePurchaseInOrder")
    @Operation(summary = "分页查询采购入库单", description = "分页查询采购入库单")
    public BaseResponse pagePurchaseInOrder(PurchaseInOrderQueryDTO purchaseInOrderQueryDTO) {
        return BaseResponse.success(purchaseInOrderService.pagePurchaseInOrder(purchaseInOrderQueryDTO));
    }

    /**
     * 查询采购入库单列表
     *
     * @param purchaseInOrder
     * @return
     */
    @GetMapping("/listPurchaseInOrder")
    @Operation(summary = "查询采购入库单列表", description = "查询采购入库单列表")
    public BaseResponse listPurchaseInOrder(@RequestBody PurchaseInOrder purchaseInOrder) {
        return BaseResponse.success(purchaseInOrderService.listPurchaseInOrder(purchaseInOrder));
    }

    /**
     * 根据id查询采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @GetMapping("/selectPurchaseInOrderById")
    @Operation(summary = "根据id查询采购入库单", description = "根据id查询采购入库单")
    public BaseResponse selectPurchaseInOrderById(Integer purchaseInOrderId) {
        return BaseResponse.success(purchaseInOrderService.selectPurchaseInOrderById(purchaseInOrderId));
    }

    /**
     * 新增采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    @PostMapping("/addPurchaseInOrder")
    @Operation(summary = "新增采购入库单", description = "新增采购入库单")
    public BaseResponse addPurchaseInOrder(@RequestBody PurchaseInOrderDTO purchaseInOrderDTO) {
        return BaseResponse.success(purchaseInOrderService.addPurchaseInOrder(purchaseInOrderDTO));
    }

    /**
     * 修改采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    @PostMapping("/updatePurchaseInOrder")
    @Operation(summary = "修改采购入库单", description = "修改采购入库单")
    public BaseResponse updatePurchaseInOrder(@RequestBody PurchaseInOrderDTO purchaseInOrderDTO) {
        return BaseResponse.success(purchaseInOrderService.updatePurchaseInOrder(purchaseInOrderDTO));
    }

    /**
     * 删除采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @PostMapping("/deletePurchaseInOrder")
    @Operation(summary = "删除采购入库单", description = "删除采购入库单")
    public BaseResponse deletePurchaseInOrder(Integer purchaseInOrderId) {
        return BaseResponse.success(purchaseInOrderService.deletePurchaseInOrder(purchaseInOrderId));
    }

    /**
     * 审核采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @PostMapping("/auditPurchaseInOrder")
    @Operation(summary = "审核采购入库单", description = "审核采购入库单")
    public BaseResponse auditPurchaseInOrder(Integer purchaseInOrderId) {
        return BaseResponse.success(purchaseInOrderService.auditPurchaseInOrder(purchaseInOrderId));
    }
}
