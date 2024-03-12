package cn.code4java.springbok.constant;

/**
 * @ClassName SpringbokConstant
 * @Description: 系统常量类
 * @Author fengwensheng
 * @Date 2024/1/4
 * @Version V1.0
 **/
public interface SpringbokConstant {

    /**
     * 项目名称
     */
    String NAME = "springbok";
    /**
     * 版本号
     */
    String VERSION = "1.0.0";
    /**
     * 邮箱
     */
    String EMAIL = "springbok@163.com";
    /**
     * URL
     */
    String URL = "https://www.code4java.cn";
    /**
     * token会话额外数据
     */
    String TOKEN_SESSION_EXTRA = "tokenSessionExtra";
    /**
     * 登录失败次数
     */
    Integer LOGIN_COUNT = 3;
    /**
     * 登录失败等待时间，分钟
     */
    Integer LOGIN_FAIL_WAITING = 3;

    //------------------ Redis Key ------------------
    /**
     * 菜单缓存
     */
    String KEY_SYSTEM_MENU = "system:menu";
    /**
     * 系统字典
     */
    String KEY_SYSTEM_DICT = "system:dict";
    /**
     * 登录次数
     */
    String KEY_LOGIN_COUNT = "login:count:";
    /**
     * 验证码
     */
    String KEY_LOGIN_CAPTCHA = "login:captcha:";
    /**
     * 订单倒计时
     */
    String KEY_MALL_COUNTDOWN = "mall:countdown:";
    /**
     * 访问人数统计
     */
    String KEY_LOGIN_STATISTICS = "login:statistics";
}
