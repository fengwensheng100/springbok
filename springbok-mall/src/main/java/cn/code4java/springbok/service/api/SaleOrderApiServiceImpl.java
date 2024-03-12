package cn.code4java.springbok.service.api;

import cn.code4java.springbok.dto.api.SaleOrderCancelApiDTO;
import cn.code4java.springbok.entity.*;
import cn.code4java.springbok.entity.*;
import cn.code4java.springbok.enums.BillTypeEnum;
import cn.code4java.springbok.mapper.*;
import cn.code4java.springbok.vo.MemberCartVO;
import cn.code4java.springbok.vo.api.SaleOrderApiVO;
import cn.code4java.springbok.vo.api.SaleOrderPreApiVO;
import cn.code4java.springbok.mapper.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.dto.SaleOrderQueryDTO;
import cn.code4java.springbok.dto.StockInOutDTO;
import cn.code4java.springbok.dto.api.SaleOrderApiDTO;
import cn.code4java.springbok.dto.api.SaleOrderPreNowApiDTO;
import cn.code4java.springbok.enums.OrderStatusEnum;
import cn.code4java.springbok.enums.PayStatusEnum;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.api.SaleOrderApiMapper;
import cn.code4java.springbok.service.StockService;
import cn.code4java.springbok.utils.IdUtils;
import cn.code4java.springbok.utils.RedisUtils;
import cn.code4java.springbok.utils.StringUtils;
import cn.code4java.springbok.vo.SaleOrderLogisticVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName SaleOrderApiServiceImpl
 * @Description: 销售订单API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SaleOrderApiServiceImpl extends ServiceImpl<SaleOrderMapper, SaleOrder> implements SaleOrderApiService {

    private MemberCartApiService memberCartApiService;
    private MemberMapper memberMapper;
    private MemberAddressMapper memberAddressMapper;
    private ItemSaleSkuMapper itemSaleSkuMapper;
    private SaleOrderApiMapper saleOrderApiMapper;
    private SaleOrderLineMapper saleOrderLineMapper;
    private RedisUtils redisUtils;
    private StockService stockService;

    /**
     * 分页查询销售订单
     *
     * @param saleOrderQueryDTO
     * @return
     */
    @Override
    public Page<SaleOrderApiVO> pageSaleOrder(SaleOrderQueryDTO saleOrderQueryDTO) {
        Page<SaleOrderApiVO> page = new Page<>();
        page.setCurrent(saleOrderQueryDTO.getCurrent());
        page.setSize(saleOrderQueryDTO.getSize());
        Page<SaleOrderApiVO> saleOrderApiVOPage = saleOrderApiMapper.pageSaleOrder(page, saleOrderQueryDTO);
        if (CollectionUtil.isNotEmpty(saleOrderApiVOPage.getRecords())) {
            saleOrderApiVOPage.getRecords().stream().forEach(saleOrderApiVO -> {
                List<SaleOrderLine> saleOrderLines = saleOrderLineMapper.selectList(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrderApiVO.getSaleOrderId()));
                saleOrderApiVO.setSaleOrderLines(saleOrderLines);
            });
        }
        return saleOrderApiVOPage;
    }

    /**
     * 查询预订单
     *
     * @param cartIds
     * @return
     */
    @Override
    public SaleOrderPreApiVO selectSaleOrderPre(String cartIds) {
        SaleOrderPreApiVO saleOrderPreVO = new SaleOrderPreApiVO();
        List<MemberCartVO> memberCarts = memberCartApiService.listMemberCartByIds(cartIds);
        if (CollectionUtil.isEmpty(memberCarts)) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "购物车不存在此商品");
        }
        List<SaleOrderPreApiVO.ItemSaleSkuPre> itemSaleSkuPres = new LinkedList<>();
        memberCarts.stream().forEach(memberCart -> {
            SaleOrderPreApiVO.ItemSaleSkuPre itemSaleSkuPre = new SaleOrderPreApiVO.ItemSaleSkuPre();
            BeanUtil.copyProperties(memberCart, itemSaleSkuPre);
            itemSaleSkuPre.setPayPrice(memberCart.getPrice());
            itemSaleSkuPre.setAmount(memberCart.getPrice().multiply(memberCart.getNumber()));
            itemSaleSkuPre.setPayAmount(memberCart.getPrice().multiply(memberCart.getNumber()));
            itemSaleSkuPres.add(itemSaleSkuPre);
        });
        saleOrderPreVO.setItemSaleSkuPres(itemSaleSkuPres);

        List<MemberAddress> memberAddresses = memberAddressMapper.selectList(Wrappers.<MemberAddress>lambdaQuery().eq(MemberAddress::getMemberId, StpUtil.getLoginIdAsInt()));
        saleOrderPreVO.setMemberAddresses(memberAddresses);

        SaleOrderPreApiVO.Summary summary = new SaleOrderPreApiVO.Summary();
        // 应从运费模板中读取
        summary.setPostFee(new BigDecimal(7));
        Optional<BigDecimal> totalAmount = saleOrderPreVO.getItemSaleSkuPres().stream().map(SaleOrderPreApiVO.ItemSaleSkuPre::getAmount).reduce(BigDecimal::add);
        summary.setAmount(totalAmount.get());
        summary.setPayAmount(totalAmount.get().add(summary.getPostFee()));
        saleOrderPreVO.setSummary(summary);

        String preOrderId = UUID.randomUUID().toString();
        redisUtils.set(preOrderId, JSONUtil.toJsonStr(saleOrderPreVO), 2, TimeUnit.MINUTES);
        saleOrderPreVO.setPreOrderId(preOrderId);
        return saleOrderPreVO;
    }

    /**
     * 查询再次购买预付订单
     *
     * @param orderNo
     * @return
     */
    @Override
    @Transactional
    public SaleOrderPreApiVO selectReSaleOrderPre(String orderNo) {
        StringJoiner cartIds = new StringJoiner(",");
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, orderNo));
        if (saleOrder == null) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "订单不存在");
        }
        List<SaleOrderLine> saleOrderLines = saleOrderLineMapper.selectList(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrder.getSaleOrderId()));
        saleOrderLines.stream().forEach(saleOrderLine -> {
            MemberCart memberCart = new MemberCart();
            memberCart.setItemSaleId(saleOrderLine.getItemSaleId());
            memberCart.setItemSaleSkuId(saleOrderLine.getItemSaleSkuId());
            memberCart.setNumber(saleOrderLine.getQuantity());
            Integer memberCartId = memberCartApiService.addMemberCart(memberCart);
            cartIds.add(memberCartId.toString());
        });
        SaleOrderPreApiVO saleOrderPreApiVO = selectSaleOrderPre(cartIds.toString());
        memberCartApiService.deleteMemberCart(cartIds.toString());
        return saleOrderPreApiVO;
    }

    /**
     * 查询立即购买预付订单
     *
     * @param saleOrderPreNowApiDTO
     * @return
     */
    @Override
    @Transactional
    public SaleOrderPreApiVO selectSaleOrderPreNow(SaleOrderPreNowApiDTO saleOrderPreNowApiDTO) {
        MemberCart memberCart = new MemberCart();
        ItemSaleSku itemSaleSku = itemSaleSkuMapper.selectById(saleOrderPreNowApiDTO.getItemSaleSkuId());
        memberCart.setItemSaleId(itemSaleSku.getItemSaleId());
        memberCart.setItemSaleSkuId(itemSaleSku.getItemSaleSkuId());
        memberCart.setNumber(saleOrderPreNowApiDTO.getQuantity());
        Integer memberCartId = memberCartApiService.addMemberCart(memberCart);
        SaleOrderPreApiVO saleOrderPreApiVO = selectSaleOrderPre(memberCartId.toString());
        memberCartApiService.removeById(memberCartId);
        return saleOrderPreApiVO;
    }

    /**
     * 提交订单
     *
     * @param saleOrderApiDTO
     * @return
     */
    @Override
    @Transactional
    public String submitSaleOrder(SaleOrderApiDTO saleOrderApiDTO) {
        String preOrderJson = redisUtils.get(saleOrderApiDTO.getPreOrderId());
        if (StringUtils.isBlank(preOrderJson)) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "订单已超时，请重新提交结算");
        }
        SaleOrderPreApiVO saleOrderPreVO = JSONUtil.toBean(preOrderJson, SaleOrderPreApiVO.class);
        // 提交订单
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setOrderNo(IdUtils.generateBillNo(BillTypeEnum.SALE_OUT_ORDER));
        saleOrder.setMemberId(StpUtil.getLoginIdAsInt());
        Member member = memberMapper.selectById(saleOrder.getMemberId());
        saleOrder.setNickname(member.getNickname());
        saleOrder.setAmount(saleOrderPreVO.getSummary().getAmount());
        saleOrder.setPayAmount(saleOrderPreVO.getSummary().getPayAmount());
        saleOrder.setDiscountAmount(BigDecimal.ZERO);
        saleOrder.setPostFee(saleOrderPreVO.getSummary().getPostFee());
        saleOrder.setSaleChannel(1);
        saleOrder.setOrderStatus(OrderStatusEnum.WAITING_PAYMENT.getStatus());
        saleOrder.setPayStatus(PayStatusEnum.WAITING_PAYMENT.getStatus());
        saleOrder.setPayChannel(0);
        saleOrder.setPayType(saleOrderApiDTO.getPayType());
        Optional<BigDecimal> totalQuantity = saleOrderPreVO.getItemSaleSkuPres().stream().map(SaleOrderPreApiVO.ItemSaleSkuPre::getNumber).reduce(BigDecimal::add);
        saleOrder.setTotalQuantity(totalQuantity.get());
        saleOrder.setMemberRemark(saleOrderApiDTO.getMemberRemark());
        // 地址信息
        MemberAddress memberAddress = memberAddressMapper.selectById(saleOrderApiDTO.getMemberAddressId());
        saleOrder.setReceiverName(memberAddress.getReceiver());
        saleOrder.setReceiverTel(memberAddress.getContact());
        saleOrder.setReceiverAddress(memberAddress.getLocation() + " " + memberAddress.getAddress());
        saleOrderApiMapper.insert(saleOrder);
        saleOrderPreVO.getItemSaleSkuPres().stream().forEach(itemSaleSkuPre -> {
            SaleOrderLine saleOrderLine = new SaleOrderLine();
            saleOrderLine.setSaleOrderId(saleOrder.getSaleOrderId());
            saleOrderLine.setItemSaleId(itemSaleSkuPre.getItemSaleId());
            saleOrderLine.setItemSaleSkuId(itemSaleSkuPre.getItemSaleSkuId());
            saleOrderLine.setSkuCode(itemSaleSkuPre.getSkuCode());
            saleOrderLine.setItemSaleName(itemSaleSkuPre.getItemSaleName());
            saleOrderLine.setAttrsText(itemSaleSkuPre.getAttrsText());
            saleOrderLine.setImage(itemSaleSkuPre.getImage());
            saleOrderLine.setQuantity(itemSaleSkuPre.getNumber());
            saleOrderLine.setPrice(itemSaleSkuPre.getPrice());
            saleOrderLine.setPayPrice(itemSaleSkuPre.getPayPrice());
            saleOrderLine.setAmount(itemSaleSkuPre.getAmount());
            saleOrderLine.setPayAmount(itemSaleSkuPre.getPayAmount());
            saleOrderLineMapper.insert(saleOrderLine);
        });
        // 刷新购物车
        memberCartApiService.removeByIds(saleOrderPreVO.getItemSaleSkuPres().stream().map(SaleOrderPreApiVO.ItemSaleSkuPre::getMemberCartId).collect(Collectors.toList()));

        redisUtils.delete(saleOrderApiDTO.getPreOrderId());
        redisUtils.set(SpringbokConstant.KEY_MALL_COUNTDOWN + saleOrder.getOrderNo(), saleOrder.getOrderNo(), 2, TimeUnit.MINUTES);
        return saleOrder.getOrderNo();
    }

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @return
     */
    @Override
    public SaleOrderApiVO selectSaleOrderByOrderNo(String orderNo) {
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, orderNo));
        SaleOrderApiVO saleOrderApiVO = new SaleOrderApiVO();
        BeanUtil.copyProperties(saleOrder, saleOrderApiVO);
        List<SaleOrderLine> saleOrderLines = saleOrderLineMapper.selectList(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrder.getSaleOrderId()));
        saleOrderApiVO.setSaleOrderLines(saleOrderLines);
        saleOrderApiVO.setCountdown(redisUtils.getExpire(SpringbokConstant.KEY_MALL_COUNTDOWN + orderNo));
        return saleOrderApiVO;
    }

    /**
     * 取消订单
     *
     * @param saleOrderCancelApiDTO
     * @return
     */
    @Override
    public boolean cancelSaleOrder(SaleOrderCancelApiDTO saleOrderCancelApiDTO) {
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, saleOrderCancelApiDTO.getOrderNo()));
        if (saleOrder.getOrderStatus() != OrderStatusEnum.WAITING_PAYMENT.getStatus()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "无法取消已付款的订单");
        }
        saleOrder.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());
        saleOrder.setCancelReason(saleOrderCancelApiDTO.getCancelReason());
        return this.updateById(saleOrder);
    }

    /**
     * 删除订单
     *
     * @param orderNo
     * @return
     */
    @Override
    @Transactional
    public boolean deleteSaleOrder(String orderNo) {
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, orderNo));
        if (saleOrder.getOrderStatus() < OrderStatusEnum.WAITING_COMMENT.getStatus()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "无法删除进行中的订单");
        }
        saleOrderLineMapper.delete(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrder.getSaleOrderId()));
        return this.removeById(saleOrder.getSaleOrderId());
    }

    /**
     * 获取订单物流
     *
     * @param orderNo
     * @return
     */
    @Override
    public SaleOrderLogisticVO selectSaleOrderLogistics(String orderNo) {
        SaleOrderLogisticVO saleOrderLogisticVO = new SaleOrderLogisticVO();
        saleOrderLogisticVO.setCount(3);
        saleOrderLogisticVO.setCompanyName("顺丰速运");
        saleOrderLogisticVO.setLogisticNumber("66666666");
        saleOrderLogisticVO.setTel("15622002200");
        List<SaleOrderLogisticNode> saleOrderLogisticNodes = new LinkedList<>();
        SaleOrderLogisticNode saleOrderLogisticNode1 = new SaleOrderLogisticNode();
        saleOrderLogisticNode1.setText("快递员已揽件");
        saleOrderLogisticNode1.setTime(new Date());
        saleOrderLogisticNodes.add(saleOrderLogisticNode1);
        SaleOrderLogisticNode saleOrderLogisticNode2 = new SaleOrderLogisticNode();
        saleOrderLogisticNode2.setText("正在发往杭州转运中心");
        saleOrderLogisticNode2.setTime(new Date());
        saleOrderLogisticNodes.add(saleOrderLogisticNode2);
        SaleOrderLogisticNode saleOrderLogisticNode3 = new SaleOrderLogisticNode();
        saleOrderLogisticNode3.setText("快件已到达杭州转运中心");
        saleOrderLogisticNode3.setTime(new Date());
        saleOrderLogisticNodes.add(saleOrderLogisticNode3);
        saleOrderLogisticVO.setSaleOrderLogisticNodes(saleOrderLogisticNodes);
        return saleOrderLogisticVO;
    }

    /**
     * 模拟支付接口
     *
     * @param orderNo
     * @return
     */
    @Override
    public boolean payTest(String orderNo) {
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, orderNo));
        if (saleOrder.getPayType() != PayStatusEnum.WAITING_PAYMENT.getStatus()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单已支付，不可重复支付");
        }
        if (redisUtils.getExpire(SpringbokConstant.KEY_MALL_COUNTDOWN + orderNo) < 0) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "订单支付已超时，请重新提交订单");
        }
        saleOrder.setOrderStatus(OrderStatusEnum.WAITING_DELIVER.getStatus());
        saleOrder.setPayChannel(1);
        saleOrder.setPayStatus(PayStatusEnum.PAYMENT.getStatus());
        saleOrder.setPayTime(new Date());
        this.updateById(saleOrder);
        // 扣减库存
        List<SaleOrderLine> saleOrderLines = saleOrderLineMapper.selectList(Wrappers.<SaleOrderLine>lambdaQuery().eq(SaleOrderLine::getSaleOrderId, saleOrder.getSaleOrderId()));
        List<StockInOutDTO.StockInOutLine> stockInOutLines = new LinkedList<>();
        saleOrderLines.stream().forEach(saleOrderLine -> {
            StockInOutDTO.StockInOutLine ingredient = new StockInOutDTO.StockInOutLine();
            ingredient.setSkuCode(saleOrderLine.getSkuCode());
            ingredient.setQuantity(saleOrderLine.getQuantity().multiply(new BigDecimal(-1)));
            stockInOutLines.add(ingredient);
        });
        StockInOutDTO stockInOutDTO = new StockInOutDTO();
        stockInOutDTO.setBillType(BillTypeEnum.SALE_OUT_ORDER.getCode());
        stockInOutDTO.setOrderNo(saleOrder.getOrderNo());
        stockInOutDTO.setStockInOutLines(stockInOutLines);
        return stockService.inOutStock(stockInOutDTO);
    }

    /**
     * 确认收货
     *
     * @param orderNo
     * @return
     */
    @Override
    public boolean receiptSaleOrder(String orderNo) {
        SaleOrder saleOrder = saleOrderApiMapper.selectOne(Wrappers.<SaleOrder>lambdaQuery().eq(SaleOrder::getOrderNo, orderNo));
        if (saleOrder.getOrderStatus() != OrderStatusEnum.WAITING_RECEIVED.getStatus()) {
            throw new BusinessException(ExceptionEnum.BUSINESS_ORDER_STATUS_ERROR, "此订单非待收货状态");
        }
        saleOrder.setOrderStatus(OrderStatusEnum.FINISH.getStatus());
        return this.updateById(saleOrder);
    }
}
