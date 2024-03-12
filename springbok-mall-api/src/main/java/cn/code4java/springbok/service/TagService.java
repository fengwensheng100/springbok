package cn.code4java.springbok.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.code4java.springbok.dto.TagQueryDTO;
import cn.code4java.springbok.entity.Tag;

import java.util.List;

/**
 * @ClassName TagService
 * @Description 标签服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface TagService {
    /**
     * 分页查询标签
     *
     * @param tagQueryDTO
     * @return
     */
    Page<Tag> pageTag(TagQueryDTO tagQueryDTO);

    /**
     * 查询标签列表
     *
     * @param tag
     * @return
     */
    List<Tag> listTag(Tag tag);

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    int addTag(Tag tag);

    /**
     * 修改标签
     *
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    int deleteTag(int tagId);
}
