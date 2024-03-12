package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Member
 * @Description: 会员
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
@TableName(value = "mall_member")
public class Member extends BaseEntity {

    /**
     * 会员id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 省市区
     */
    private String location;
}
