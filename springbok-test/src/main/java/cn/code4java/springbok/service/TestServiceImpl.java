package cn.code4java.springbok.service;

import cn.code4java.springbok.entity.Test;
import cn.code4java.springbok.mapper.TestMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestServiceImpl
 * @Description: TestServiceImpl
 * @Author fengwensheng
 * @Date 2024/3/3
 * @Version V1.0
 **/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
}
