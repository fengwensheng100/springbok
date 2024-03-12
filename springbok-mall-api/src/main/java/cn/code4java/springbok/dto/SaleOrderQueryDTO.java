package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.SaleOrder;
import lombok.Data;

/**
 * @ClassName SaleOrderQueryDTO
 * @Description: SaleOrderQueryDTO
 * @Author fengwensheng
 * @Date 2023/12/21
 * @Version V1.0
 **/
@Data
public class SaleOrderQueryDTO extends SaleOrder {

    private long size;
    private long current;
}
