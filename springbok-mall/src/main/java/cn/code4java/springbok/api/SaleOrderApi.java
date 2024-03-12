package cn.code4java.springbok.api;

import cn.code4java.springbok.annotation.ApiSecurity;
import cn.code4java.springbok.annotation.Idempotent;
import cn.code4java.springbok.dto.api.SaleOrderCancelApiDTO;
import cn.code4java.springbok.service.api.SaleOrderApiService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.dto.api.SaleOrderApiDTO;
import cn.code4java.springbok.dto.api.SaleOrderPreNowApiDTO;
import cn.code4java.springbok.dto.api.SaleOrderReceiptApiDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SaleOrderApi
 * @Description: 销售订单API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saleOrder")
public class SaleOrderApi {

    private final SaleOrderApiService saleOrderApiService;

    /**
     * 查询订单列表
     *
     * @param saleOrderQueryDTO
     * @return
     */
    @GetMapping("/pageSaleOrder")
    public BaseResponse pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO) {
        return BaseResponse.success(saleOrderApiService.pageSaleOrder(saleOrderQueryDTO));
    }

    /**
     * 查询预付订单
     *
     * @param cartIds
     * @return
     */
    @GetMapping("/selectSaleOrderPre")
    public BaseResponse selectSaleOrderPre(String cartIds) {
        return BaseResponse.success(saleOrderApiService.selectSaleOrderPre(cartIds));
    }

    /**
     * 查询再次购买预付订单
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/selectReSaleOrderPre")
    public BaseResponse selectReSaleOrderPre(String orderNo) {
        return BaseResponse.success(saleOrderApiService.selectReSaleOrderPre(orderNo));
    }

    /**
     * 查询立即购买预付订单
     *
     * @param saleOrderPreNowApiDTO
     * @return
     */
    @GetMapping("/selectSaleOrderPreNow")
    public BaseResponse selectSaleOrderPreNow(SaleOrderPreNowApiDTO saleOrderPreNowApiDTO) {
        return BaseResponse.success(saleOrderApiService.selectSaleOrderPreNow(saleOrderPreNowApiDTO));
    }

    /**
     * 提交订单
     *
     * @param saleOrderApiDTO
     * @return
     */
    @Idempotent(expireTime = 10, message = "请勿重复提交订单")
    @PostMapping("/submitSaleOrder")
    public BaseResponse submitSaleOrder(@RequestBody SaleOrderApiDTO saleOrderApiDTO) {
        return BaseResponse.success(saleOrderApiService.submitSaleOrder(saleOrderApiDTO));
    }

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/selectSaleOrderByOrderNo")
    public BaseResponse selectSaleOrderByOrderNo(String orderNo) {
        return BaseResponse.success(saleOrderApiService.selectSaleOrderByOrderNo(orderNo));
    }

    /**
     * 取消订单
     *
     * @param saleOrderCancelApiDTO
     * @return
     */
    @GetMapping("/cancelSaleOrder")
    public BaseResponse cancelSaleOrder(@RequestBody SaleOrderCancelApiDTO saleOrderCancelApiDTO) {
        return BaseResponse.success(saleOrderApiService.cancelSaleOrder(saleOrderCancelApiDTO));
    }

    /**
     * 删除订单
     *
     * @param orderNo
     * @return
     */
    @PostMapping("/deleteSaleOrder")
    public BaseResponse deleteSaleOrder(String orderNo) {
        return BaseResponse.success(saleOrderApiService.deleteSaleOrder(orderNo));
    }

    /**
     * 确认收货
     *
     * @param saleOrderReceiptApiDTO
     * @return
     */
    @PostMapping("/receiptSaleOrder")
    public BaseResponse receiptSaleOrder(@RequestBody SaleOrderReceiptApiDTO saleOrderReceiptApiDTO) {
        return BaseResponse.success(saleOrderApiService.receiptSaleOrder(saleOrderReceiptApiDTO.getOrderNo()));
    }

    /**
     * 获取订单物流
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/selectSaleOrderLogistics")
    public BaseResponse selectSaleOrderLogistics(String orderNo) {
        return BaseResponse.success(saleOrderApiService.selectSaleOrderLogistics(orderNo));
    }

    /**
     * 模拟支付接口
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/payTest")
    public BaseResponse payTest(String orderNo) {
        return BaseResponse.success(saleOrderApiService.payTest(orderNo));
    }
}
