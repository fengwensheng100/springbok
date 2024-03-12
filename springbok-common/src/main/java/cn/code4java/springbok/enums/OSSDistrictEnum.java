package cn.code4java.springbok.enums;

import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;

/**
 * @ClassName OSSDistrictEnum
 * @Description: 上传图片资源分区目录
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public enum OSSDistrictEnum {

    /**
     * 商品
     */
    ITEM(1, "item"),
    /**
     * 系统
     */
    SYSTEM(2, "system"),
    /**
     * 采购
     */
    PURCHASE(3, "purchase"),
    /**
     * 用户
     */
    USER(4, "user");

    private int type;
    private String value;

    OSSDistrictEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static OSSDistrictEnum getOSSDistrictEnum(int type) {
        for (OSSDistrictEnum ossDistrictEnum : OSSDistrictEnum.values()) {
            if (ossDistrictEnum.getType() == type) {
                return ossDistrictEnum;
            }
        }
        throw new BusinessException(ExceptionEnum.PARAM_ERROR, "分区不存在");
    }
}
