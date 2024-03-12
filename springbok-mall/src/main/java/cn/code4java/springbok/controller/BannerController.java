package cn.code4java.springbok.controller;

import cn.code4java.springbok.entity.Banner;
import cn.code4java.springbok.vo.BaseResponse;
import cn.code4java.springbok.dto.BannerQueryDTO;
import cn.code4java.springbok.service.BannerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BannerController
 * @Description: 广告模块控制器
 * @Author fengwensheng
 * @Date 2023/11/21
 * @Version V1.0
 **/
@Tag(name = "广告管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    /**
     * 分页查询广告
     *
     * @param params
     * @return
     */
    @GetMapping("/pageBanner")
    @Operation(summary = "分页查询广告", description = "分页查询广告")
    public BaseResponse pageBanner(BannerQueryDTO params) {
        return BaseResponse.success(bannerService.pageBanner(params));
    }

    /**
     * 查询广告列表
     *
     * @param banner
     * @return
     */
    @GetMapping("/listBanner")
    @Operation(summary = "查询广告列表", description = "查询广告列表")
    public BaseResponse listBanner(Banner banner) {
        return BaseResponse.success(bannerService.listBanner(banner));
    }

    /**
     * 根据id查询广告
     *
     * @param bannerId
     * @return
     */
    @GetMapping("/selectBannerById")
    @Operation(summary = "根据id查询广告", description = "根据id查询广告")
    public BaseResponse selectBannerById(Integer bannerId) {
        return BaseResponse.success(bannerService.selectBannerById(bannerId));
    }

    /**
     * 新增广告
     *
     * @param banner
     * @return
     */
    @PostMapping("/addBanner")
    @Operation(summary = "新增广告", description = "新增广告")
    public BaseResponse addBanner(@RequestBody Banner banner) {
        return BaseResponse.success(bannerService.addBanner(banner));
    }

    /**
     * 修改广告
     *
     * @param banner
     * @return
     */
    @PostMapping("/updateBanner")
    @Operation(summary = "修改广告", description = "修改广告")
    public BaseResponse updateBanner(@RequestBody Banner banner) {
        return BaseResponse.success(bannerService.updateBanner(banner));
    }

    /**
     * 删除广告
     *
     * @param bannerId
     * @return
     */
    @PostMapping("/deleteBanner")
    @Operation(summary = "删除广告", description = "删除广告")
    public BaseResponse deleteBanner(Integer bannerId) {
        return BaseResponse.success(bannerService.deleteBanner(bannerId));
    }
}
