package cn.code4java.springbok.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName TokenSessionVO
 * @Description: token会话数据
 * @Author fengwensheng
 * @Date 2024/1/5
 * @Version V1.0
 **/
@Data
public class TokenSessionVO {
    /**
     * 昵称
     */
    private String username;
    /**
     * 昵称
     */
    private List<String> permissionCodes;
}
