package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.PurchaseOutOrderDTO;
import cn.code4java.springbok.dto.PurchaseOutOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseOutOrder;
import cn.code4java.springbok.vo.PurchaseOutOrderVO;

import java.util.List;

/**
 * @ClassName PurchaseOutOrderService
 * @Description: 采购出库单服务类
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseOutOrderService {
    /**
     * 分页查询采购出库单
     *
     * @param purchaseOutOrderQueryDTO
     * @return
     */
    Page<PurchaseOutOrderVO> pagePurchaseOutOrder(PurchaseOutOrderQueryDTO purchaseOutOrderQueryDTO);

    /**
     * 查询采购出库单列表
     *
     * @param purchaseOutOrder
     * @return
     */
    List<PurchaseOutOrder> listPurchaseOutOrder(PurchaseOutOrder purchaseOutOrder);

    /**
     * 根据id查询采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    PurchaseOutOrderVO selectPurchaseOutOrderById(int purchaseOutOrderId);

    /**
     * 新增采购出库单
     *
     * @param purchaseOutOrderDTO
     * @return
     */
    int addPurchaseOutOrder(PurchaseOutOrderDTO purchaseOutOrderDTO);

    /**
     * 修改采购出库单
     *
     * @param purchaseOutOrderDTO
     * @return
     */
    int updatePurchaseOutOrder(PurchaseOutOrderDTO purchaseOutOrderDTO);

    /**
     * 删除采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    int deletePurchaseOutOrder(int purchaseOutOrderId);

    /**
     * 审核采购出库单
     *
     * @param purchaseOutOrderId
     * @return
     */
    boolean auditPurchaseOutOrder(int purchaseOutOrderId);
}
