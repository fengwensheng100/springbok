package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.api.SaleOrderCancelApiDTO;
import cn.code4java.springbok.vo.api.SaleOrderApiVO;
import cn.code4java.springbok.vo.api.SaleOrderPreApiVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.dto.api.SaleOrderApiDTO;
import cn.code4java.springbok.dto.api.SaleOrderPreNowApiDTO;
import cn.code4java.springbok.entity.SaleOrder;
import cn.code4java.springbok.vo.SaleOrderLogisticVO;

/**
 * @ClassName SaleOrderApiService
 * @Description: 销售订单API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface SaleOrderApiService extends IService<SaleOrder> {
    /**
     * 分页查询销售订单
     *
     * @param saleOrderQueryDTO
     * @return
     */
    Page<SaleOrderApiVO> pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO);

    /**
     * 查询预订单
     *
     * @param cartIds
     * @return
     */
    SaleOrderPreApiVO selectSaleOrderPre(String cartIds);

    /**
     * 查询再次购买预付订单
     *
     * @param orderNo
     * @return
     */
    SaleOrderPreApiVO selectReSaleOrderPre(String orderNo);

    /**
     * 查询立即购买预付订单
     *
     * @param saleOrderPreNowApiDTO
     * @return
     */
    SaleOrderPreApiVO selectSaleOrderPreNow(SaleOrderPreNowApiDTO saleOrderPreNowApiDTO);

    /**
     * 提交订单
     *
     * @param saleOrderApiDTO
     * @return
     */
    String submitSaleOrder(SaleOrderApiDTO saleOrderApiDTO);

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @return
     */
    SaleOrderApiVO selectSaleOrderByOrderNo(String orderNo);

    /**
     * 取消订单
     *
     * @param saleOrderCancelApiDTO
     * @return
     */
    boolean cancelSaleOrder(SaleOrderCancelApiDTO saleOrderCancelApiDTO);

    /**
     * 删除订单
     *
     * @param orderNo
     * @return
     */
    boolean deleteSaleOrder(String orderNo);

    /**
     * 获取订单物流
     *
     * @param orderNo
     * @return
     */
    SaleOrderLogisticVO selectSaleOrderLogistics(String orderNo);

    /**
     * 模拟支付接口
     *
     * @param orderNo
     * @return
     */
    boolean payTest(String orderNo);

    /**
     * 确认收货
     *
     * @param orderNo
     * @return
     */
    boolean receiptSaleOrder(String orderNo);
}
