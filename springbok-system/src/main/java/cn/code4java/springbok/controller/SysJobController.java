package cn.code4java.springbok.controller;

import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.dto.SysJobQueryDTO;
import cn.code4java.springbok.entity.SysJob;
import cn.code4java.springbok.service.SysJobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysJobController
 * @Description: 定时任务控制器
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Tag(name = "定时任务管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class SysJobController {

    private final SysJobService sysJobService;

    /**
     * 分页查询定时任务
     *
     * @param sysJobQueryDTO
     * @return
     */
    @GetMapping("/pageSysJob")
    @Operation(summary = "分页查询定时任务", description = "分页查询日志")
    public BaseResponse pageSysJob(SysJobQueryDTO sysJobQueryDTO) {
        return BaseResponse.success(sysJobService.pageSysJob(sysJobQueryDTO));
    }

    /**
     * 根据id查询定时任务
     *
     * @param sysJobId
     * @return
     */
    @GetMapping("/selectSysJobById")
    @Operation(summary = "根据id查询定时任务", description = "根据id查询角色信息")
    public BaseResponse selectSysJobById(@Parameter(description = "任务id", required = true) Integer sysJobId) {
        return BaseResponse.success(sysJobService.selectSysJobById(sysJobId));
    }

    /**
     * 新增定时任务
     *
     * @param sysJob
     * @return
     * @throws SchedulerException
     */
    @Log(title = "新增定时任务")
    @PostMapping("/addSysJob")
    @Operation(summary = "新增定时任务", description = "新增定时任务")
    public BaseResponse addSysJob(@RequestBody SysJob sysJob) throws SchedulerException {
        sysJobService.addSysJob(sysJob);
        return BaseResponse.success();
    }

    /**
     * 修改定时任务
     *
     * @param sysJob
     * @return
     * @throws SchedulerException
     */
    @Log(title = "修改定时任务")
    @PostMapping("/updateSysJob")
    @Operation(summary = "修改定时任务", description = "修改定时任务")
    public BaseResponse updateSysJob(@RequestBody SysJob sysJob) throws SchedulerException {
        sysJobService.updateSysJob(sysJob);
        return BaseResponse.success();
    }

    /**
     * 删除定时任务
     *
     * @param sysJobId
     * @return
     * @throws SchedulerException
     */
    @Log(title = "删除定时任务")
    @PostMapping("/deleteSysJob")
    @Operation(summary = "删除定时任务", description = "删除定时任务")
    public BaseResponse deleteSysJob(@Parameter(description = "任务id", required = true) Integer sysJobId) throws SchedulerException {
        sysJobService.deleteSysJob(sysJobId);
        return BaseResponse.success();
    }

    /**
     * 手动执行一次定时任务
     *
     * @param sysJob
     * @return
     * @throws SchedulerException
     */
    @Log(title = "手动执行一次定时任务")
    @PostMapping("/executeOnce")
    @Operation(summary = "手动执行一次定时任务", description = "手动执行一次定时任务")
    public BaseResponse executeOnce(@RequestBody SysJob sysJob) throws SchedulerException {
        sysJobService.executeOnce(sysJob.getJobKey(), sysJob.getJobGroup());
        return BaseResponse.success();
    }

    /**
     * 启用关闭定时任务
     *
     * @param sysJobId
     * @param status
     * @return
     * @throws SchedulerException
     */
    @Log(title = "启用关闭定时任务")
    @PostMapping("/enabledSysJob")
    @Operation(summary = "启用关闭定时任务", description = "启用关闭定时任务")
    public BaseResponse enabledSysJob(@Parameter(description = "任务id", required = true) @RequestParam("sysJobId") Integer sysJobId,
                                      @Parameter(description = "状态 1：启用 2：停用", required = true) @RequestParam("status")Integer status) throws SchedulerException {
        sysJobService.enabledSysJob(sysJobId, status);
        return BaseResponse.success();
    }
}
