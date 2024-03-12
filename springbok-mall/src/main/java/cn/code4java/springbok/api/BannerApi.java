package cn.code4java.springbok.api;

import cn.code4java.springbok.vo.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.code4java.springbok.entity.Banner;
import cn.code4java.springbok.service.api.BannerApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BannerApi
 * @Description: 广告模块API
 * @Author fengwensheng
 * @Date 2024/1/18
 * @Version V1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerApi {

    private final BannerApiService bannerApiService;

    /**
     * 查询广告列表
     *
     * @param banner
     * @return
     */
    @GetMapping("/listBanner")
    public BaseResponse listBanner(Banner banner) {
        return BaseResponse.success(bannerApiService.list(Wrappers.<Banner>lambdaQuery().eq(Banner::getStatus, 1)));
    }
}
