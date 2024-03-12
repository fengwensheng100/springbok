package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.SaleOrderLogistic;
import cn.code4java.springbok.entity.SaleOrderLogisticNode;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SaleOrderLogisticVO
 * @Description: SaleOrderLogisticVO
 * @Author fengwensheng
 * @Date 2024/1/31
 * @Version V1.0
 **/
@Data
public class SaleOrderLogisticVO extends SaleOrderLogistic {

    private List<SaleOrderLogisticNode> saleOrderLogisticNodes;
}
