package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.PurchaseInOrderDTO;
import cn.code4java.springbok.dto.PurchaseInOrderQueryDTO;
import cn.code4java.springbok.dto.StockInOutDTO;
import cn.code4java.springbok.entity.PurchaseInOrder;
import cn.code4java.springbok.entity.PurchaseInOrderLine;
import cn.code4java.springbok.enums.BillTypeEnum;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.PurchaseInOrderLineMapper;
import cn.code4java.springbok.mapper.PurchaseInOrderMapper;
import cn.code4java.springbok.vo.PurchaseInOrderLineVO;
import cn.code4java.springbok.vo.PurchaseInOrderVO;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.utils.IdUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName PurchaseInOrderServiceImpl
 * @Description: 采购入库单服务实现类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class PurchaseInOrderServiceImpl extends ServiceImpl<PurchaseInOrderMapper, PurchaseInOrder> implements PurchaseInOrderService {

    private PurchaseInOrderMapper purchaseInOrderMapper;
    private PurchaseInOrderLineMapper purchaseInOrderLineMapper;
    private StockService stockService;

    /**
     * 分页查询采购入库单
     *
     * @param purchaseInOrderQueryDTO
     * @return
     */
    @Override
    public Page<PurchaseInOrderVO> pagePurchaseInOrder(PurchaseInOrderQueryDTO purchaseInOrderQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(purchaseInOrderQueryDTO.getCurrent());
        page.setSize(purchaseInOrderQueryDTO.getSize());
        return purchaseInOrderMapper.pagePurchaseInOrder(page, purchaseInOrderQueryDTO);
    }

    /**
     * 查询采购入库单列表
     *
     * @param purchaseInOrder
     * @return
     */
    @Override
    public List<PurchaseInOrder> listPurchaseInOrder(PurchaseInOrder purchaseInOrder) {
        LambdaQueryWrapper wrappers = new LambdaQueryWrapper();
        return purchaseInOrderMapper.selectList(wrappers);
    }

    /**
     * 根据id查询采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @Override
    public PurchaseInOrderVO selectPurchaseInOrderById(int purchaseInOrderId) {
        PurchaseInOrderVO purchaseInOrderVO = new PurchaseInOrderVO();
        PurchaseInOrder purchaseInOrder = purchaseInOrderMapper.selectById(purchaseInOrderId);
        BeanUtils.copyProperties(purchaseInOrder, purchaseInOrderVO);
        List<PurchaseInOrderLineVO> purchaseInOrderLineVOS = purchaseInOrderLineMapper.listByPurchaseInOrderId(purchaseInOrderId);
        purchaseInOrderVO.setPurchaseInOrderLineList(purchaseInOrderLineVOS);
        return purchaseInOrderVO;
    }

    /**
     * 新增采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    @Override
    @Transactional
    public int addPurchaseInOrder(PurchaseInOrderDTO purchaseInOrderDTO) {
        if (CollectionUtil.isEmpty(purchaseInOrderDTO.getPurchaseInOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "订单明细不能为空");
        }
        BigDecimal orderAmount = purchaseInOrderDTO.getPurchaseInOrderLineList().stream()
                .map(PurchaseInOrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseInOrderDTO.setOrderAmount(orderAmount);
        purchaseInOrderDTO.setOrderStatus(1);
        purchaseInOrderDTO.setOrderNo(IdUtils.generateBillNo(BillTypeEnum.PURCHASE_IN_ORDER));
        purchaseInOrderMapper.insert(purchaseInOrderDTO);
        purchaseInOrderDTO.getPurchaseInOrderLineList().stream().forEach(purchaseInOrderLine -> {
            purchaseInOrderLine.setPurchaseInOrderId(purchaseInOrderDTO.getPurchaseInOrderId());
            purchaseInOrderLineMapper.insert(purchaseInOrderLine);
        });
        return 1;
    }

    /**
     * 修改采购入库单
     *
     * @param purchaseInOrderDTO
     * @return
     */
    @Override
    @Transactional
    public int updatePurchaseInOrder(PurchaseInOrderDTO purchaseInOrderDTO) {
        if (CollectionUtil.isEmpty(purchaseInOrderDTO.getPurchaseInOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "订单明细不能为空");
        }
        purchaseInOrderLineMapper.delete(new LambdaQueryWrapper<PurchaseInOrderLine>()
                .eq(PurchaseInOrderLine::getPurchaseInOrderId, purchaseInOrderDTO.getPurchaseInOrderId()));
        BigDecimal orderAmount = purchaseInOrderDTO.getPurchaseInOrderLineList().stream()
                .map(PurchaseInOrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseInOrderDTO.setOrderAmount(orderAmount);
        purchaseInOrderMapper.updateById(purchaseInOrderDTO);
        purchaseInOrderDTO.getPurchaseInOrderLineList().stream().forEach(purchaseInOrderLine -> {
            purchaseInOrderLine.setPurchaseInOrderId(purchaseInOrderDTO.getPurchaseInOrderId());
            purchaseInOrderLineMapper.insert(purchaseInOrderLine);
        });
        return 1;
    }

    /**
     * 删除采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @Override
    public int deletePurchaseInOrder(int purchaseInOrderId) {
        PurchaseInOrder purchaseInOrder = purchaseInOrderMapper.selectById(purchaseInOrderId);
        if (purchaseInOrder.getOrderStatus() > 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非制单状态");
        }
        purchaseInOrderMapper.deleteById(purchaseInOrderId);
        purchaseInOrderLineMapper.delete(new LambdaQueryWrapper<PurchaseInOrderLine>().eq(PurchaseInOrderLine::getPurchaseInOrderId, purchaseInOrderId));
        return 1;
    }

    /**
     * 审核采购入库单
     *
     * @param purchaseInOrderId
     * @return
     */
    @Override
    @Transactional
    public boolean auditPurchaseInOrder(int purchaseInOrderId) {
        PurchaseInOrder purchaseInOrder = purchaseInOrderMapper.selectById(purchaseInOrderId);
        if (purchaseInOrder.getOrderStatus() > 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非制单状态");
        }
        purchaseInOrder.setOrderStatus(2);
        purchaseInOrderMapper.updateById(purchaseInOrder);
        List<PurchaseInOrderLine> purchaseInOrderLines = purchaseInOrderLineMapper.selectList(new LambdaQueryWrapper<PurchaseInOrderLine>()
                .eq(PurchaseInOrderLine::getPurchaseInOrderId, purchaseInOrder.getPurchaseInOrderId()));
        //入库
        List<StockInOutDTO.StockInOutLine> stockInOutLines = new LinkedList<>();
        purchaseInOrderLines.stream().forEach(purchaseInOrderLine -> {
            StockInOutDTO.StockInOutLine ingredient = new StockInOutDTO.StockInOutLine();
            ingredient.setSkuCode(purchaseInOrderLine.getSkuCode());
            ingredient.setQuantity(purchaseInOrderLine.getQuantity());
            stockInOutLines.add(ingredient);
        });
        StockInOutDTO stockInOutDTO = new StockInOutDTO();
        stockInOutDTO.setBillType(BillTypeEnum.PURCHASE_IN_ORDER.getCode());
        stockInOutDTO.setOrderNo(purchaseInOrder.getOrderNo());
        stockInOutDTO.setStockInOutLines(stockInOutLines);
        return stockService.inOutStock(stockInOutDTO);
    }
}
