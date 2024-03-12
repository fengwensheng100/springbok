package cn.code4java.springbok.service;

import cn.code4java.springbok.dto.TagQueryDTO;
import cn.code4java.springbok.entity.Tag;
import cn.code4java.springbok.mapper.TagMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description: 标签服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private TagMapper tagMapper;

    /**
     * 分页查询标签
     *
     * @param tagQueryDTO
     * @return
     */
    @Override
    public Page<Tag> pageTag(TagQueryDTO tagQueryDTO) {
        Page page = new Page<>();
        page.setCurrent(tagQueryDTO.getCurrent());
        page.setSize(tagQueryDTO.getSize());
        return tagMapper.pageTag(page, tagQueryDTO);
    }

    /**
     * 查询标签列表
     *
     * @param tag
     * @return
     */
    @Override
    public List<Tag> listTag(Tag tag) {
        return tagMapper.selectList(new LambdaQueryWrapper<Tag>()
                .like(StringUtils.isNotBlank(tag.getTagName()), Tag::getTagName, tag.getTagName())
                .eq(tag.getTagType() > 0, Tag::getTagType, tag.getTagType()));
    }

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    @Override
    public int addTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    /**
     * 修改标签
     *
     * @param tag
     * @return
     */
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateById(tag);
    }

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    @Override
    public int deleteTag(int tagId) {
        return tagMapper.deleteById(tagId);
    }
}
