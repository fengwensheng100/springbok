package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.SysUserQueryDTO;
import cn.code4java.springbok.entity.SysUser;
import cn.code4java.springbok.enums.LoginDriverEnum;
import cn.code4java.springbok.vo.TokenSessionVO;
import cn.code4java.springbok.vo.UserTokenVO;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.dto.LoginDTO;
import cn.code4java.springbok.entity.SysMenu;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.mapper.SysMenuMapper;
import cn.code4java.springbok.mapper.SysUserMapper;
import cn.code4java.springbok.utils.RedisUtils;
import cn.code4java.springbok.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName SysUserServiceImpl
 * @Description: 用户服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private SysUserMapper sysUserMapper;
    private SysMenuMapper sysMenuMapper;
    private RedisUtils redisUtils;

    /**
     * 分页查询用户
     *
     * @param sysUserQueryDTO
     * @return
     */
    @Override
    public Page<SysUser> pageSysUser(SysUserQueryDTO sysUserQueryDTO) {
        Page<SysUser> page = new Page<>();
        page.setCurrent(sysUserQueryDTO.getCurrent());
        page.setSize(sysUserQueryDTO.getSize());
        return sysUserMapper.pageSysUser(page, sysUserQueryDTO);
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @Override
    public int addSysUser(SysUser sysUser) {
        sysUser.setSalt(IdUtil.fastUUID());
        String password = MD5.create().digestHex(sysUser.getUserPassword() + sysUser.getSalt());
        sysUser.setUserPassword(password);
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        SysUser user = sysUserMapper.selectById(sysUser.getSysUserId());
        if (!user.getUserPassword().equals(sysUser.getUserPassword())) {
            sysUser.setSalt(IdUtil.fastUUID());
            String password = MD5.create().digestHex(sysUser.getUserPassword() + sysUser.getSalt());
            sysUser.setUserPassword(password);
        }
        return sysUserMapper.updateById(sysUser);
    }

    /**
     * 删除用户
     *
     * @param sysUserId
     * @return
     */
    @Override
    public int deleteSysUser(int sysUserId) {
        return sysUserId > 0 ? sysUserMapper.deleteById(sysUserId) : 0;
    }

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    @Override
    public UserTokenVO login(LoginDTO loginDTO) {
        if (loginDTO.getVerifyMethod().equals("captcha")) {
            if (!redisUtils.hasKey(SpringbokConstant.KEY_LOGIN_CAPTCHA + loginDTO.getCaptcha())) {
                throw new BusinessException(ExceptionEnum.BUSINESS_PARAM_ERROR, "验证码错误或已过期");
            }
        }
        String username = loginDTO.getUsername();
        String password = loginDTO.getUserPassword();
        String loginKey = SpringbokConstant.KEY_LOGIN_COUNT + username;
        String loginCount = redisUtils.get(loginKey);
        if (StringUtils.isNotBlank(loginCount) && Integer.valueOf(loginCount) >= SpringbokConstant.LOGIN_COUNT) {
            throw new BusinessException(ExceptionEnum.BUSINESS_LOGIN_PASSWORD_ERROR, "失败次数过多，请" + redisUtils.getExpire(loginKey, TimeUnit.MINUTES) + "分钟后重试");
        }
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_ABSENT_ERROR, "用户不存在");
        }
        if (!MD5.create().digestHex(password + sysUser.getSalt()).equals(sysUser.getUserPassword())) {
            redisUtils.set(loginKey, StringUtils.isNotBlank(loginCount) ? String.valueOf(Integer.valueOf(loginCount) + 1) : "1", SpringbokConstant.LOGIN_FAIL_WAITING, TimeUnit.MINUTES);
            throw new BusinessException(ExceptionEnum.BUSINESS_LOGIN_PASSWORD_ERROR, ExceptionEnum.BUSINESS_LOGIN_PASSWORD_ERROR.getMessage());
        }
        List<String> permissionCodes = new ArrayList<>();
        List<SysMenu> sysMenuList = sysMenuMapper.listSysMenuByUserId(sysUser.getSysUserId());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            permissionCodes = sysMenuList.stream().filter(sysMenu -> StringUtils.isNotBlank(sysMenu.getPermissionCode()))
                    .map(SysMenu::getPermissionCode)
                    .collect(Collectors.toList());
        }
        // 登录
        StpUtil.login(sysUser.getSysUserId(), LoginDriverEnum.WEB.name());
        TokenSessionVO tokenSessionVO = new TokenSessionVO();
        tokenSessionVO.setUsername(sysUser.getUsername());
        tokenSessionVO.setPermissionCodes(permissionCodes);
        StpUtil.getTokenSession().set(SpringbokConstant.TOKEN_SESSION_EXTRA, tokenSessionVO);
        // 登录成功
        redisUtils.delete(loginKey);
        // 访问人数+1
        Object hash = redisUtils.getHash(SpringbokConstant.KEY_LOGIN_STATISTICS, DateUtil.format(new Date(), "yyyy-MM-dd"));
        if (ObjectUtil.isNull(hash)) {
            redisUtils.setHash(SpringbokConstant.KEY_LOGIN_STATISTICS, DateUtil.format(new Date(), "yyyy-MM-dd"), "1");
        } else {
            redisUtils.setHash(SpringbokConstant.KEY_LOGIN_STATISTICS, DateUtil.format(new Date(), "yyyy-MM-dd"), String.valueOf(Integer.valueOf(hash.toString()) + 1));
        }
        UserTokenVO userTokenVO = new UserTokenVO();
        userTokenVO.setSysUserId(sysUser.getSysUserId());
        userTokenVO.setNickName(sysUser.getNickName());
        userTokenVO.setToken(StpUtil.getTokenValue());
        return userTokenVO;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        StpUtil.logout();
    }
}
