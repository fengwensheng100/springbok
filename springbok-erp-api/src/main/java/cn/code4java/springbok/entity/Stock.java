package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @ClassName Stock
 * @Description: 库存资料
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Data
@TableName(value = "erp_stock")
public class Stock extends BaseEntity {

    /**
     * 库存id
     */
    @TableId(type = IdType.AUTO)
    private Integer stockId;
    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 库存量
     */
    private BigDecimal quantity;
    /**
     * 成本金额
     */
    private BigDecimal costAmount;
}
