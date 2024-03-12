package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.BannerQueryDTO;
import cn.code4java.springbok.entity.Banner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @ClassName BannerService
 * @Description: 广告模块服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface BannerService {
    /**
     * 分页查询广告
     *
     * @param bannerQueryDTO
     * @return
     */
    Page<Banner> pageBanner(BannerQueryDTO bannerQueryDTO);

    /**
     * 查询广告列表
     *
     * @param banner
     * @return
     */
    List<Banner> listBanner(Banner banner);

    /**
     * 根据id查询广告
     *
     * @param id
     * @return
     */
    Banner selectBannerById(Integer id);

    /**
     * 新增广告
     *
     * @param banner
     * @return
     */
    boolean addBanner(Banner banner);

    /**
     * 修改广告
     *
     * @param banner
     * @return
     */
    boolean updateBanner(Banner banner);

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    boolean deleteBanner(Integer id);
}
