package cn.code4java.springbok.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName UserTokenVO
 * @Description: UserTokenVO
 * @Author fengwensheng
 * @Date 2024/1/5
 * @Version V1.0
 **/
@Data
public class UserTokenVO {

    /**
     * 用户id
     */
    private Integer sysUserId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 登录凭证
     */
    private String token;
}
