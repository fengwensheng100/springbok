package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.SysJob;
import cn.code4java.springbok.entity.SysJobLog;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SysJobVO
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
public class SysJobVO extends SysJob {

    private List<SysJobLog> sysJobLogs;
}
