package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.PurchaseOutOrderDTO;
import cn.code4java.springbok.dto.PurchaseOutOrderQueryDTO;
import cn.code4java.springbok.dto.StockInOutDTO;
import cn.code4java.springbok.entity.PurchaseOutOrder;
import cn.code4java.springbok.entity.PurchaseOutOrderLine;
import cn.code4java.springbok.enums.BillTypeEnum;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.PurchaseOutOrderLineMapper;
import cn.code4java.springbok.mapper.PurchaseOutOrderMapper;
import cn.code4java.springbok.vo.PurchaseOutOrderLineVO;
import cn.code4java.springbok.vo.PurchaseOutOrderVO;
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
 * @ClassName PurchaseOutOrderServiceImpl
 * @Description: 采购出库单服务实现类
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class PurchaseOutOrderServiceImpl extends ServiceImpl<PurchaseOutOrderMapper, PurchaseOutOrder> implements PurchaseOutOrderService {

    private PurchaseOutOrderMapper purchaseOutOrderMapper;
    private PurchaseOutOrderLineMapper purchaseOutOrderLineMapper;
    private StockService stockService;

    @Override
    public Page<PurchaseOutOrderVO> pagePurchaseOutOrder(PurchaseOutOrderQueryDTO purchaseOutOrderQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(purchaseOutOrderQueryDTO.getCurrent());
        page.setSize(purchaseOutOrderQueryDTO.getSize());
        return purchaseOutOrderMapper.pagePurchaseOutOrder(page, purchaseOutOrderQueryDTO);
    }

    @Override
    public List<PurchaseOutOrder> listPurchaseOutOrder(PurchaseOutOrder purchaseOutOrder) {
        LambdaQueryWrapper wrappers = new LambdaQueryWrapper();
        return purchaseOutOrderMapper.selectList(wrappers);
    }

    @Override
    public PurchaseOutOrderVO selectPurchaseOutOrderById(int purchaseOutOrderId) {
        PurchaseOutOrderVO purchaseOutOrderVO = new PurchaseOutOrderVO();
        PurchaseOutOrder purchaseOutOrder = purchaseOutOrderMapper.selectById(purchaseOutOrderId);
        BeanUtils.copyProperties(purchaseOutOrder, purchaseOutOrderVO);
        List<PurchaseOutOrderLineVO> purchaseInOrderLineVOS = purchaseOutOrderLineMapper.listByPurchaseOutOrderId(purchaseOutOrderId);
        purchaseOutOrderVO.setPurchaseOutOrderLineList(purchaseInOrderLineVOS);
        return purchaseOutOrderVO;
    }

    @Override
    public int addPurchaseOutOrder(PurchaseOutOrderDTO purchaseOutOrderDTO) {
        if (CollectionUtil.isEmpty(purchaseOutOrderDTO.getPurchaseOutOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "订单明细不能为空");
        }
        purchaseOutOrderLineMapper.delete(new LambdaQueryWrapper<PurchaseOutOrderLine>()
                .eq(PurchaseOutOrderLine::getPurchaseOutOrderId, purchaseOutOrderDTO.getPurchaseOutOrderId()));
        BigDecimal orderAmount = purchaseOutOrderDTO.getPurchaseOutOrderLineList().stream()
                .map(PurchaseOutOrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseOutOrderDTO.setOrderAmount(orderAmount);
        purchaseOutOrderDTO.setOrderStatus(1);
        purchaseOutOrderDTO.setOrderNo(IdUtils.generateBillNo(BillTypeEnum.PURCHASE_OUT_ORDER));
        purchaseOutOrderMapper.insert(purchaseOutOrderDTO);
        purchaseOutOrderDTO.getPurchaseOutOrderLineList().stream().forEach(purchaseOutOrderLine -> {
            purchaseOutOrderLine.setPurchaseOutOrderId(purchaseOutOrderDTO.getPurchaseOutOrderId());
            purchaseOutOrderLineMapper.insert(purchaseOutOrderLine);
        });
        return purchaseOutOrderDTO.getPurchaseOutOrderId();
    }

    @Override
    public int updatePurchaseOutOrder(PurchaseOutOrderDTO purchaseOutOrderDTO) {
        if (CollectionUtil.isEmpty(purchaseOutOrderDTO.getPurchaseOutOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, "订单明细不能为空");
        }
        purchaseOutOrderLineMapper.delete(new LambdaQueryWrapper<PurchaseOutOrderLine>()
                .eq(PurchaseOutOrderLine::getPurchaseOutOrderId, purchaseOutOrderDTO.getPurchaseOutOrderId()));
        BigDecimal orderAmount = purchaseOutOrderDTO.getPurchaseOutOrderLineList().stream()
                .map(PurchaseOutOrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseOutOrderDTO.setOrderAmount(orderAmount);
        purchaseOutOrderMapper.updateById(purchaseOutOrderDTO);
        purchaseOutOrderDTO.getPurchaseOutOrderLineList().stream().forEach(purchaseOutOrderLine -> {
            purchaseOutOrderLine.setPurchaseOutOrderId(purchaseOutOrderDTO.getPurchaseOutOrderId());
            purchaseOutOrderLineMapper.insert(purchaseOutOrderLine);
        });
        return purchaseOutOrderDTO.getPurchaseOutOrderId();
    }

    @Override
    public int deletePurchaseOutOrder(int purchaseOutOrderId) {
        PurchaseOutOrder purchaseOutOrder = purchaseOutOrderMapper.selectById(purchaseOutOrderId);
        if (purchaseOutOrder.getOrderStatus() > 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非制单状态");
        }
        purchaseOutOrderMapper.deleteById(purchaseOutOrderId);
        purchaseOutOrderLineMapper.delete(new LambdaQueryWrapper<PurchaseOutOrderLine>().eq(PurchaseOutOrderLine::getPurchaseOutOrderId, purchaseOutOrderId));
        return purchaseOutOrderId;
    }

    @Override
    @Transactional
    public boolean auditPurchaseOutOrder(int purchaseOutOrderId) {
        PurchaseOutOrder purchaseOutOrder = purchaseOutOrderMapper.selectById(purchaseOutOrderId);
        if (purchaseOutOrder.getOrderStatus() > 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非制单状态");
        }
        purchaseOutOrder.setOrderStatus(2);
        purchaseOutOrderMapper.updateById(purchaseOutOrder);
        List<PurchaseOutOrderLine> purchaseOutOrderLines = purchaseOutOrderLineMapper.selectList(new LambdaQueryWrapper<PurchaseOutOrderLine>()
                .eq(PurchaseOutOrderLine::getPurchaseOutOrderId, purchaseOutOrder.getPurchaseOutOrderId()));
        //出库
        List<StockInOutDTO.StockInOutLine> stockInOutLines = new LinkedList<>();
        purchaseOutOrderLines.stream().forEach(purchaseOutOrderLine -> {
            StockInOutDTO.StockInOutLine ingredient = new StockInOutDTO.StockInOutLine();
            ingredient.setSkuCode(purchaseOutOrderLine.getSkuCode());
            ingredient.setQuantity(purchaseOutOrderLine.getQuantity().multiply(new BigDecimal(-1)));
            stockInOutLines.add(ingredient);
        });
        StockInOutDTO stockInOutDTO = new StockInOutDTO();
        stockInOutDTO.setBillType(BillTypeEnum.PURCHASE_OUT_ORDER.getCode());
        stockInOutDTO.setOrderNo(purchaseOutOrder.getOrderNo());
        stockInOutDTO.setStockInOutLines(stockInOutLines);
        return stockService.inOutStock(stockInOutDTO);
    }
}
