package cn.code4java.springbok.vo.api;

import cn.code4java.springbok.entity.Member;
import lombok.Data;

/**
 * @ClassName MemberApiVO
 * @Description: MemberApiVO
 * @Author fengwensheng
 * @Date 2024/1/29
 * @Version V1.0
 **/
@Data
public class MemberApiVO extends Member {

    /**
     * 登录凭证
     */
    private String token;
}
