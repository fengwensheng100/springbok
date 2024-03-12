package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SysConfig
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2024/2/21
 * @Version V1.0
 **/
@Data
@Schema(title = "系统配置实体")
@TableName(value = "sys_config")
public class SysConfig {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @Schema(title = "id")
    private Integer sysConfigId;
    /**
     * 标签
     */
    @Schema(title = "标签")
    private String label;
    /**
     * 类型
     * radio：单选
     * switch：开关
     */
    @Schema(title = "类型 radio：单选 switch：开关")
    private String type;
    /**
     * 可选值
     * 当type为switch：可选值为空，configValue只为0（关闭）或1（开启）
     */
    @Schema(title = "可选值")
    private String configOptions;
    /**
     * 配置key
     * @see cn.code4java.springbok.enums.SysConfigKeyEnum
     */
    @Schema(title = "配置key")
    private String configKey;
    /**
     * 当前值
     */
    @Schema(title = "当前值")
    private String configValue;

    /**
     * 反序列化后的可选值
     */
    @TableField(exist = false)
    private List<ConfigOptions> configOptionsList;

    @Data
    public static class ConfigOptions{
        private String label;
        private String value;
    };
}
