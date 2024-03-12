package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName MemberAddress
 * @Description: 会员地址
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_member_address")
public class MemberAddress {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberAddressId;
    /**
     * 会员id
     */
    private Integer memberId;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 省市区
     */
    private String location;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 区域编码
     */
    private String countyCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否默认地址
     */
    private Boolean defaultAddress;
}
