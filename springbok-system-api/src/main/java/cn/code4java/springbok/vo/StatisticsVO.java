package cn.code4java.springbok.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName StatisticsVO
 * @Description: StatisticsVO
 * @Author fengwensheng
 * @Date 2024/2/4
 * @Version V1.0
 **/
@Data
public class StatisticsVO {
    /**
     * 订单数
     */
    private Integer orderCount;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 待发货订单数
     */
    private Integer waitingDeliverCount;
    /**
     * 会员数
     */
    private Integer memberCount;
    /**
     * 商品数
     */
    private Integer itemSaleCount;
    /**
     * 访问人数统计
     */
    private List<LoginStatistics> loginStatistics;
    @Data
    public static class LoginStatistics {
        /**
         * 日期
         */
        private String date;
        /**
         * 登录次数
         */
        private Long count;
    }
    /**
     * 本月商品销售额前十统计
     */
    private List<ItemSaleStatisticsVO> topItemSaleStatisticsByMonth;
}
