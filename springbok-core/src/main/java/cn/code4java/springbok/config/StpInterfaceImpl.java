package cn.code4java.springbok.config;

import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.utils.StringUtils;
import cn.code4java.springbok.vo.TokenSessionVO;
import cn.code4java.springbok.vo.UserTokenVO;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StpInterfaceImpl
 * @Description: 自定义权限加载接口实现类
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        /**
         * 从token session中取出用户的权限码
         */
        TokenSessionVO tokenSessionVO = (TokenSessionVO) StpUtil.getTokenSession().get(SpringbokConstant.TOKEN_SESSION_EXTRA);
        return tokenSessionVO.getPermissionCodes();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
