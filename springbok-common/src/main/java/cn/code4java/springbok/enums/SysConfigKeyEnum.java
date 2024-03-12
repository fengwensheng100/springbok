package cn.code4java.springbok.enums;

/**
 * @ClassName SysConfigKeyEnum
 * @Description: 系统配置类型枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum SysConfigKeyEnum {

    /**
     * 登录验证方式
     * captcha：验证码
     * slider：滑块验证
     */
    VERIFY_METHOD("登录验证方式", new String[]{"captcha", "slider"});

    private String desc;
    private String[] options;

    SysConfigKeyEnum(String desc, String[] options) {
        this.desc = desc;
        this.options = options;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getOptions() {
        return options;
    }
}
