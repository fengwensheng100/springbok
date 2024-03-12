package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.InventoryOrderDTO;
import cn.code4java.springbok.dto.InventoryOrderQueryDTO;
import cn.code4java.springbok.dto.StockInOutDTO;
import cn.code4java.springbok.entity.InventoryOrder;
import cn.code4java.springbok.entity.InventoryOrderLine;
import cn.code4java.springbok.entity.Stock;
import cn.code4java.springbok.enums.BillTypeEnum;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.InventoryOrderLineMapper;
import cn.code4java.springbok.mapper.InventoryOrderMapper;
import cn.code4java.springbok.mapper.StockMapper;
import cn.code4java.springbok.vo.InventoryOrderLineVO;
import cn.code4java.springbok.vo.InventoryOrderVO;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.utils.IdUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName InventoryOrderServiceImpl
 * @Description: 盘点订单服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class InventoryOrderServiceImpl extends ServiceImpl<InventoryOrderMapper, InventoryOrder> implements InventoryOrderService {

    private InventoryOrderMapper inventoryOrderMapper;
    private InventoryOrderLineMapper inventoryOrderLineMapper;
    private StockMapper stockMapper;
    private StockService stockService;

    /**
     * 分页查询盘点订单
     *
     * @param inventoryOrderQueryDTO
     * @return
     */
    @Override
    public Page<InventoryOrderVO> pageInventoryOrder(InventoryOrderQueryDTO inventoryOrderQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(inventoryOrderQueryDTO.getCurrent());
        page.setSize(inventoryOrderQueryDTO.getSize());
        return inventoryOrderMapper.pageInventoryOrder(page, inventoryOrderQueryDTO);
    }

    /**
     * 查询盘点订单列表
     *
     * @param inventoryOrder
     * @return
     */
    @Override
    public List<InventoryOrder> listInventoryOrder(InventoryOrder inventoryOrder) {
        LambdaQueryWrapper wrappers = new LambdaQueryWrapper();
        return inventoryOrderMapper.selectList(wrappers);
    }

    /**
     * 根据id查询盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @Override
    public InventoryOrderVO selectInventoryOrderById(int inventoryOrderId) {
        InventoryOrderVO inventoryOrderVO = new InventoryOrderVO();
        InventoryOrder inventoryOrder = inventoryOrderMapper.selectById(inventoryOrderId);
        BeanUtils.copyProperties(inventoryOrder, inventoryOrderVO);
        List<InventoryOrderLineVO> inventoryOrderLineVOS = inventoryOrderLineMapper.listByInventoryOrderId(inventoryOrderId);
        inventoryOrderVO.setInventoryOrderLineList(inventoryOrderLineVOS);
        return inventoryOrderVO;
    }

    /**
     * 新增盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    @Override
    @Transactional
    public int addInventoryOrder(InventoryOrderDTO inventoryOrderDTO) {
        if (CollectionUtil.isEmpty(inventoryOrderDTO.getInventoryOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        inventoryOrderDTO.setOrderStatus(1);
        inventoryOrderDTO.setOrderNo(IdUtils.generateBillNo(BillTypeEnum.INVENTORY_ORDER));
        inventoryOrderMapper.insert(inventoryOrderDTO);
        inventoryOrderDTO.getInventoryOrderLineList().stream().forEach(inventoryOrderLine -> {
            inventoryOrderLine.setInventoryOrderId(inventoryOrderDTO.getInventoryOrderId());
            inventoryOrderLineMapper.insert(inventoryOrderLine);
        });
        return 1;
    }

    /**
     * 修改盘点订单
     *
     * @param inventoryOrderDTO
     * @return
     */
    @Override
    @Transactional
    public int updateInventoryOrder(InventoryOrderDTO inventoryOrderDTO) {
        if (CollectionUtil.isEmpty(inventoryOrderDTO.getInventoryOrderLineList())) {
            throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR, ExceptionEnum.BUSINESS_PARAM_NOT_NULL_ERROR.getMessage());
        }
        inventoryOrderLineMapper.delete(new LambdaQueryWrapper<InventoryOrderLine>().eq(InventoryOrderLine::getInventoryOrderId, inventoryOrderDTO.getInventoryOrderId()));
        inventoryOrderMapper.updateById(inventoryOrderDTO);
        inventoryOrderDTO.getInventoryOrderLineList().stream().forEach(inventoryOrderLine -> {
            inventoryOrderLine.setInventoryOrderId(inventoryOrderDTO.getInventoryOrderId());
            inventoryOrderLineMapper.insert(inventoryOrderLine);
        });
        return 1;
    }

    /**
     * 删除盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @Override
    @Transactional
    public int deleteInventoryOrder(int inventoryOrderId) {
        inventoryOrderMapper.deleteById(inventoryOrderId);
        return inventoryOrderLineMapper.delete(new LambdaQueryWrapper<InventoryOrderLine>().eq(InventoryOrderLine::getInventoryOrderId, inventoryOrderId));
    }

    /**
     * 审核盘点订单
     *
     * @param inventoryOrderId
     * @return
     */
    @Override
    @Transactional
    public boolean auditInventoryOrder(int inventoryOrderId) {
        InventoryOrder inventoryOrder = inventoryOrderMapper.selectById(inventoryOrderId);
        if (inventoryOrder.getOrderStatus() > 1) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单非制单状态");
        }
        inventoryOrder.setOrderStatus(2);
        inventoryOrderMapper.updateById(inventoryOrder);
        List<InventoryOrderLine> inventoryOrderLines = inventoryOrderLineMapper.selectList(new LambdaQueryWrapper<InventoryOrderLine>()
                .eq(InventoryOrderLine::getInventoryOrderId, inventoryOrder.getInventoryOrderId()));
        //判断实盘数量与系统库存差异，同步库存
        List<StockInOutDTO.StockInOutLine> stockInOutLines = new LinkedList<>();
        StockInOutDTO stockInOutDTO = new StockInOutDTO();
        stockInOutDTO.setBillType(BillTypeEnum.INVENTORY_ORDER.getCode());
        stockInOutDTO.setOrderNo(inventoryOrder.getOrderNo());
        List<Stock> stocks = stockMapper.selectList(new LambdaQueryWrapper<Stock>()
                .in(Stock::getSkuCode, inventoryOrderLines.stream().map(InventoryOrderLine::getSkuCode).distinct().collect(Collectors.toList())));
        inventoryOrderLines.stream().forEach(inventoryOrderLine -> {
            Optional<Stock> first = stocks.stream().filter(stock -> stock.getSkuCode() == inventoryOrderLine.getSkuCode()).findFirst();
            StockInOutDTO.StockInOutLine stockInOutLine = new StockInOutDTO.StockInOutLine();
            stockInOutLine.setSkuCode(inventoryOrderLine.getSkuCode());
            if (first.isPresent()) {
                //与实际库存差异
                Stock stock = first.get();
                stockInOutLine.setQuantity(inventoryOrderLine.getRealQuantity().subtract(stock.getQuantity()));
            } else {
                stockInOutLine.setQuantity(inventoryOrderLine.getRealQuantity());
            }
            stockInOutLines.add(stockInOutLine);
        });
        stockInOutDTO.setStockInOutLines(stockInOutLines);
        return stockService.inOutStock(stockInOutDTO);
    }
}
