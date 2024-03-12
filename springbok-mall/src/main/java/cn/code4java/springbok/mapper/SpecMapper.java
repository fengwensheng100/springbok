package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.SpecQueryDTO;
import cn.code4java.springbok.entity.Spec;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName SpecMapper
 * @Description: SpecMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface SpecMapper extends BaseMapper<Spec> {
    Page<Spec> pageSpec(Page<Spec> page, @Param(value = "query") SpecQueryDTO propertyQueryDTO);
}
