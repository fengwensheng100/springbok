package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName StockLine
 * @Description: 库存资料明细
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_stock_line")
public class StockLine extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer stockLineId;
    /**
     * 库存id
     */
    private Integer stockId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 门店id
     */
    private Integer branchId;
    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 库存量
     */
    private BigDecimal quantity;
    /**
     * 订单类型
     * @see cn.code4java.springbok.enums.BillTypeEnum
     */
    private Integer orderType;
}
