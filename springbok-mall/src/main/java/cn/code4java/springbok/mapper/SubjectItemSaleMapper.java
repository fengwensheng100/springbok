package cn.code4java.springbok.mapper;

import cn.code4java.springbok.entity.SubjectItemSale;
import cn.code4java.springbok.vo.SubjectItemSaleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName SubjectItemSaleMapper
 * @Description: SubjectItemSaleMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface SubjectItemSaleMapper extends BaseMapper<SubjectItemSale> {
    List<SubjectItemSaleVO> selectSubjectItemSale(int subjectId);
}
