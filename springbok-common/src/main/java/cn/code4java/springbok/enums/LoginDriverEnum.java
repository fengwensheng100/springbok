package cn.code4java.springbok.enums;

/**
 * @ClassName LoginDriverEnum
 * @Description: 登录设备类型枚举
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum LoginDriverEnum {

    /**
     * web端
     */
    WEB("web端"),
    /**
     * 移动端
     */
    APP("移动端");

    private String desc;

    LoginDriverEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
