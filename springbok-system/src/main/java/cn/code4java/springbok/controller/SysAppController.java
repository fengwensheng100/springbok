package cn.code4java.springbok.controller;

import cn.code4java.springbok.entity.SysApp;
import cn.code4java.springbok.service.SysAppService;
import cn.code4java.springbok.vo.BaseResponse;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.dto.SysAppQueryDTO;
import cn.code4java.springbok.exception.BusinessException;
import cn.code4java.springbok.exception.ExceptionEnum;
import cn.code4java.springbok.utils.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysAppController
 * @Description: 应用控制器
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Tag(name = "应用管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class SysAppController {

    private final SysAppService sysAppService;

    /**
     * 分页查询应用
     *
     * @param params
     * @return
     */
    @GetMapping("/pageSysApp")
    @Operation(summary = "分页查询应用", description = "分页查询应用")
    public BaseResponse pageSysApp(SysAppQueryDTO params) {
        Page page = new Page<>();
        page.setCurrent(params.getCurrent());
        page.setSize(params.getSize());
        return BaseResponse.success(sysAppService.page(page, Wrappers.<SysApp>lambdaQuery().eq(StringUtils.isNotBlank(params.getAppId()), SysApp::getAppId, params.getAppId())));
    }

    /**
     * 新增应用
     *
     * @param sysApp
     * @return
     */
    @Log(title = "新增应用")
    @PostMapping("/addSysApp")
    @Operation(summary = "新增应用", description = "新增应用")
    public BaseResponse addSysApp(@RequestBody SysApp sysApp) {
        SysApp app = sysAppService.getOne(Wrappers.<SysApp>lambdaQuery().eq(SysApp::getAppId, sysApp.getAppId()));
        if (app != null) {
            throw new BusinessException(ExceptionEnum.BUSINESS_DATA_EXIST_ERROR, "应用已存在");
        }
        sysApp.setAppSecret(RandomUtil.randomString(24));
        return BaseResponse.success(sysAppService.save(sysApp));
    }

    /**
     * 修改应用
     *
     * @param sysApp
     * @return
     */
    @Log(title = "修改应用")
    @PostMapping("/updateSysApp")
    @Operation(summary = "修改应用", description = "修改应用")
    public BaseResponse updateSysApp(@RequestBody SysApp sysApp) {
        return BaseResponse.success(sysAppService.updateById(sysApp));
    }

    /**
     * 删除应用
     *
     * @param sysAppId
     * @return
     */
    @Log(title = "删除应用")
    @PostMapping("/deleteSysApp")
    @Operation(summary = "删除应用", description = "删除应用")
    public BaseResponse deleteSysApp(@Parameter(description = "id", required = true) Integer sysAppId) {
        return BaseResponse.success(sysAppService.removeById(sysAppId));
    }
}
