package cn.code4java.springbok.controller;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.dto.SysJobLogQueryDTO;
import cn.code4java.springbok.service.SysJobLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysJobLogController
 * @Description: 定时任务日志控制器
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Tag(name = "定时任务日志管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobLog")
public class SysJobLogController {

    private final SysJobLogService sysJobLogService;

    /**
     * 分页查询定时任务日志
     *
     * @param sysJobLogQueryDTO
     * @return
     */
    @GetMapping("/pageSysJobLog")
    @Operation(summary = "分页查询定时任务日志", description = "分页查询日志日志")
    public BaseResponse pageSysJobLog(SysJobLogQueryDTO sysJobLogQueryDTO) {
        return BaseResponse.success(sysJobLogService.pageSysJobLog(sysJobLogQueryDTO));
    }
}
