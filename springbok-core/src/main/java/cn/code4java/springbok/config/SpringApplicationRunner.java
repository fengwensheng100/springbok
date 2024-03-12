package cn.code4java.springbok.config;

import cn.code4java.springbok.constant.SpringbokConstant;
import cn.code4java.springbok.service.DictService;
import cn.code4java.springbok.vo.DictVO;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.code4java.springbok.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SpringApplicationRunner
 * @Description: 项目初始化配置
 * @Author fengwensheng
 * @Date 2024/1/3
 * @Version V1.0
 **/
@Component
@AllArgsConstructor
public class SpringApplicationRunner implements ApplicationRunner {

    private RedisUtils redisUtils;
    private DictService dictService;

    @Override
    public void run(ApplicationArguments args) {
        //缓存字典
        List<DictVO> dictVOList = dictService.listDict(null);
        if (CollectionUtil.isNotEmpty(dictVOList)) {
            Map<String, String> dictMap = new HashMap<>();
            dictVOList.stream().forEach(dictVO -> {
                dictMap.put(dictVO.getDictCode(), JSONUtil.toJsonStr(dictVO.getDictValueList()));
            });
            redisUtils.delete(SpringbokConstant.KEY_SYSTEM_DICT);
            redisUtils.setHash(SpringbokConstant.KEY_SYSTEM_DICT, dictMap);
        }
    }
}
