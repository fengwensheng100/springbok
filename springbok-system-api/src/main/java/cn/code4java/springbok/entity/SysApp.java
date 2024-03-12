package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @ClassName SysApp
 * @Description: 应用类
 * @Author fengwensheng
 * @Date 2024/1/3
 * @Version V1.0
 **/
@Data
@Schema(title = "应用实体")
@TableName(value = "sys_app")
public class SysApp {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer sysAppId;

    /**
     * 应用id
     */
    @Schema(title = "应用id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String appId;

    /**
     * 密钥
     */
    @Schema(title = "密钥")
    private String appSecret;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;
}
