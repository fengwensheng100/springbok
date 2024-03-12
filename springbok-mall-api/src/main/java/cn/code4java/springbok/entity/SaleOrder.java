package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName SaleOrder
 * @Description: 销售订单
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_sale_order")
public class SaleOrder extends BaseEntity {

    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer saleOrderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户id
     */
    private Integer memberId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 订单总额
     */
    private BigDecimal amount;
    /**
     * 订单实付
     */
    private BigDecimal payAmount;
    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;
    /**
     * 运费
     */
    private BigDecimal postFee;
    /**
     * 收货人
     */
    private String receiverName;
    /**
     * 收货人联系方式
     */
    private String receiverTel;
    /**
     * 收货人地址
     */
    private String receiverAddress;
    /**
     * 订单销售来源渠道
     * 1：小程序
     */
    private Integer saleChannel;
    /**
     * 订单状态
     * 1：待付款
     * 2：待发货
     * 3：待收货
     * 4：待评价
     * 5：已完成
     * 6：已取消
     */
    private Integer orderStatus;
    /**
     * 支付状态
     * 1：未支付
     * 2：已支付
     */
    private Integer payStatus;
    /**
     * 支付渠道
     * 1：微信支付
     * 2：支付宝支付
     */
    private Integer payChannel;
    /**
     * 支付类型
     * 1：在线支付
     */
    private Integer payType;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 总商品件数
     */
    private BigDecimal totalQuantity;
    /**
     * 用户备注
     */
    private String memberRemark;
    /**
     * 取消原因
     */
    private String cancelReason;
}
