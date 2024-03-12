package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.TagQueryDTO;
import cn.code4java.springbok.entity.Tag;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName TagMapper
 * @Description: TagMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface TagMapper extends BaseMapper<Tag> {
    Page<Tag> pageTag(Page sysUserPage, @Param(value = "query") TagQueryDTO tagQueryDTO);
}
