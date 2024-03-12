package cn.code4java.springbok.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.Banner;
import cn.code4java.springbok.mapper.BannerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName BannerApiServiceImpl
 * @Description: 广告API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class BannerApiServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerApiService {
}
