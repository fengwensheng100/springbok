package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.InventoryOrderDTO;
import cn.code4java.springbok.dto.InventoryOrderQueryDTO;
import cn.code4java.springbok.entity.InventoryOrder;
import cn.code4java.springbok.vo.InventoryOrderVO;

import java.util.List;

/**
 * @ClassName InventoryOrderService
 * @Description: 盘点订单服务类
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface InventoryOrderService {
    /**
     * 分页查询盘点订单
     *
     * @param inventoryOrderQueryDTO
     * @return
     */
    Page<InventoryOrderVO> pageInventoryOrder(InventoryOrderQueryDTO inventoryOrderQueryDTO);

    /**
     * 查询盘点订单列表
     *
     * @param inventoryOrder
     * @return
     */
    List<InventoryOrder> listInventoryOrder(InventoryOrder inventoryOrder);

    /**
     * 根据id查询盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    InventoryOrderVO selectInventoryOrderById(int inventoryOrderId);

    /**
     * 新增盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    int addInventoryOrder(InventoryOrderDTO inventoryOrderDTO);

    /**
     * 修改盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    int updateInventoryOrder(InventoryOrderDTO inventoryOrderDTO);

    /**
     * 删除盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    int deleteInventoryOrder(int inventoryOrderId);

    /**
     * 审核盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    boolean auditInventoryOrder(int inventoryOrderId);
}
