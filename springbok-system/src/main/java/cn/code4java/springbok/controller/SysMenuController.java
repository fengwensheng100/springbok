package cn.code4java.springbok.controller;

import cn.code4java.springbok.annotation.Log;
import cn.code4java.springbok.entity.SysMenu;
import cn.code4java.springbok.service.SysMenuService;
import cn.code4java.springbok.vo.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysMenuController
 * @Description: 菜单控制器
 * @Author fengwensheng
 * @Date 2023/12/20
 * @Version V1.0
 **/
@Tag(name = "菜单管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    /**
     * 查询菜单列表
     *
     * @return
     */
    @GetMapping("/listSysMenu")
    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    public BaseResponse listSysMenu() {
        return BaseResponse.success(sysMenuService.listSysMenu(null));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu
     * @return
     */
    @Log(title = "新增菜单")
    @PostMapping("/addSysMenu")
    @Operation(summary = "新增菜单", description = "新增菜单")
    public BaseResponse addSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addSysMenu(sysMenu);
        return BaseResponse.success();
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     * @return
     */
    @Log(title = "修改菜单")
    @PostMapping("/updateSysMenu")
    @Operation(summary = "修改菜单", description = "修改菜单")
    public BaseResponse updateSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return BaseResponse.success();
    }

    /**
     * 删除菜单
     *
     * @param sysMenuId
     * @return
     */
    @Log(title = "删除菜单")
    @PostMapping("/deleteSysMenu")
    @Operation(summary = "删除菜单", description = "删除菜单")
    public BaseResponse deleteSysMenu(@Parameter(description = "菜单id", required = true) Integer sysMenuId) {
        sysMenuService.deleteSysMenu(sysMenuId);
        return BaseResponse.success();
    }
}
