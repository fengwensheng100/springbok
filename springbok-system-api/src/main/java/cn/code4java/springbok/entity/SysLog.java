package cn.code4java.springbok.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysLog
 * @Description: 日志类
 * @Author fengwensheng
 * @Date 2024/1/3
 * @Version V1.0
 **/
@Data
@TableName(value = "sys_log")
public class SysLog {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer sysLogId;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 日志状态
     * 1：正常
     * 2：错误
     */
    private Integer status;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求耗时
     */
    private Long requestSpend;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 错误信息
     */
    private String errorMsg;
}
