package cn.code4java.springbok.controller;

import cn.code4java.springbok.service.SysLogService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.dto.SysLogQueryDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysLogController
 * @Description: 日志控制器
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "日志管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 分页查询日志
     *
     * @param sysLogQueryDTO
     * @return
     */
    @GetMapping("/pageSysLog")
    @Operation(summary = "分页查询日志", description = "分页查询日志")
    public BaseResponse pageSysLog(SysLogQueryDTO sysLogQueryDTO) {
        return BaseResponse.success(sysLogService.pageSysLog(sysLogQueryDTO));
    }
}
