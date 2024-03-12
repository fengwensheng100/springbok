package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.BannerQueryDTO;
import cn.code4java.springbok.entity.Banner;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.mapper.BannerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BannerServiceImpl
 * @Description: 广告模块服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    /**
     * 分页查询广告
     *
     * @param bannerQueryDTO
     * @return
     */
    @Override
    public Page<Banner> pageBanner(BannerQueryDTO bannerQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(bannerQueryDTO.getCurrent());
        page.setSize(bannerQueryDTO.getSize());
        return page(page, Wrappers.lambdaUpdate(bannerQueryDTO));
    }

    /**
     * 查询广告列表
     *
     * @param banner
     * @return
     */
    @Override
    public List<Banner> listBanner(Banner banner) {
        return list(Wrappers.lambdaQuery(banner));
    }

    /**
     * 根据id查询广告
     *
     * @param id
     * @return
     */
    @Override
    public Banner selectBannerById(Integer id) {
        return getById(id);
    }

    /**
     * 新增广告
     *
     * @param banner
     * @return
     */
    @Override
    public boolean addBanner(Banner banner) {
        return save(banner);
    }

    /**
     * 修改广告
     *
     * @param banner
     * @return
     */
    @Override
    public boolean updateBanner(Banner banner) {
        return updateById(banner);
    }

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBanner(Integer id) {
        return removeById(id);
    }
}
