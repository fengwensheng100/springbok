package cn.code4java.springbok.dto;

import cn.code4java.springbok.entity.Subject;
import cn.code4java.springbok.entity.SubjectItemSale;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SubjectDTO
 * @Description: SubjectDTO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
@Data
public class SubjectDTO extends Subject {

    private List<SubjectItemSale> subjectItemSaleList;
}
