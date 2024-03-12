package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.SysApp;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.mapper.SysAppMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysAppServiceImpl
 * @Description: 应用服务实现类
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Service
public class SysAppServiceImpl extends ServiceImpl<SysAppMapper, SysApp> implements SysAppService {
}
