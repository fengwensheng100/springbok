package cn.code4java.springbok.vo;

import cn.code4java.springbok.entity.Subject;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SubjectVO
 * @Description: SubjectVO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class SubjectVO extends Subject {

    private List<SubjectItemSaleVO> subjectItemSaleList;
}
