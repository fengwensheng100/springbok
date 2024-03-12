package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.PurchaseInOrderDTO;
import cn.code4java.springbok.dto.PurchaseInOrderQueryDTO;
import cn.code4java.springbok.entity.PurchaseInOrder;
import cn.code4java.springbok.vo.PurchaseInOrderVO;

import java.util.List;

/**
 * @ClassName PurchaseInOrderService
 * @Description: 采购入库单服务类
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface PurchaseInOrderService {
    /**
     * 分页查询采购入库单
     *
     * @param purchaseInOrderQueryDTO
     * @return
     */
    Page<PurchaseInOrderVO> pagePurchaseInOrder(PurchaseInOrderQueryDTO purchaseInOrderQueryDTO);

    /**
     * 查询采购入库单列表
     *
     * @param purchaseInOrder
     * @return
     */
    List<PurchaseInOrder> listPurchaseInOrder(PurchaseInOrder purchaseInOrder);

    /**
     * 根据id查询采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    PurchaseInOrderVO selectPurchaseInOrderById(int purchaseInOrderId);

    /**
     * 新增采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    int addPurchaseInOrder(PurchaseInOrderDTO purchaseInOrderDTO);

    /**
     * 修改采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    int updatePurchaseInOrder(PurchaseInOrderDTO purchaseInOrderDTO);

    /**
     * 删除采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    int deletePurchaseInOrder(int purchaseInOrderId);

    /**
     * 审核采购入库单
     * @param purchaseInOrderId
     * @return
     */
    boolean auditPurchaseInOrder(int purchaseInOrderId);
}
