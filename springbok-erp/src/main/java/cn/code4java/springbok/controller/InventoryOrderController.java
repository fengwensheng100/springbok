package cn.code4java.springbok.controller;

import cn.code4java.springbok.dto.InventoryOrderDTO;
import cn.code4java.springbok.dto.InventoryOrderQueryDTO;
import cn.code4java.springbok.entity.InventoryOrder;
import cn.code4java.springbok.service.InventoryOrderService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName InventoryOrderController
 * @Description: 盘点订单控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "盘点订单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventoryOrder")
public class InventoryOrderController {

    private final InventoryOrderService inventoryOrderService;

    /**
     * 分页查询盘点订单
     *
     * @param inventoryOrderQueryDTO
     * @return
     */
    @GetMapping("/pageInventoryOrder")
    @Operation(summary = "分页查询盘点订单", description = "分页查询盘点订单")
    public BaseResponse pageInventoryOrder(InventoryOrderQueryDTO inventoryOrderQueryDTO) {
        return BaseResponse.success(inventoryOrderService.pageInventoryOrder(inventoryOrderQueryDTO));
    }

    /**
     * 查询盘点订单列表
     *
     * @param inventoryOrder
     * @return
     */
    @GetMapping("/listInventoryOrder")
    @Operation(summary = "查询盘点订单列表", description = "查询盘点订单列表")
    public BaseResponse listInventoryOrder(@RequestBody InventoryOrder inventoryOrder) {
        return BaseResponse.success(inventoryOrderService.listInventoryOrder(inventoryOrder));
    }

    /**
     * 根据id查询盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @GetMapping("/selectInventoryOrderById")
    @Operation(summary = "根据id查询盘点订单", description = "根据id查询盘点订单")
    public BaseResponse selectInventoryOrderById(Integer inventoryOrderId) {
        return BaseResponse.success(inventoryOrderService.selectInventoryOrderById(inventoryOrderId));
    }

    /**
     * 新增盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    @PostMapping("/addInventoryOrder")
    @Operation(summary = "新增盘点订单", description = "新增盘点订单")
    public BaseResponse addInventoryOrder(@RequestBody InventoryOrderDTO inventoryOrderDTO) {
        return BaseResponse.success(inventoryOrderService.addInventoryOrder(inventoryOrderDTO));
    }

    /**
     * 修改盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    @PostMapping("/updateInventoryOrder")
    @Operation(summary = "修改盘点订单", description = "修改盘点订单")
    public BaseResponse updateInventoryOrder(@RequestBody InventoryOrderDTO inventoryOrderDTO) {
        return BaseResponse.success(inventoryOrderService.updateInventoryOrder(inventoryOrderDTO));
    }

    /**
     * 删除盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @PostMapping("/deleteInventoryOrder")
    @Operation(summary = "删除盘点订单", description = "删除盘点订单")
    public BaseResponse deleteInventoryOrder(Integer inventoryOrderId) {
        return BaseResponse.success(inventoryOrderService.deleteInventoryOrder(inventoryOrderId));
    }

    /**
     * 审核盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @PostMapping("/auditInventoryOrder")
    @Operation(summary = "审核盘点订单", description = "审核盘点订单")
    public BaseResponse auditInventoryOrder(Integer inventoryOrderId) {
        return BaseResponse.success(inventoryOrderService.auditInventoryOrder(inventoryOrderId));
    }
}
