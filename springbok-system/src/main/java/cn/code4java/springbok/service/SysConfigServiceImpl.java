package cn.code4java.springbok.service;

import cn.code4java.springbok.mapper.SysConfigMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.SysConfig;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysConfigServiceImpl
 * @Description: 系统配置服务实现类
 * @Author fengwensheng
 * @Date 2024/2/21
 * @Version V1.0
 **/
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
}
