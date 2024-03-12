package cn.code4java.springbok.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.code4java.springbok.entity.MemberCart;
import cn.code4java.springbok.vo.MemberCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName MemberCartMapper
 * @Description: MemberCartMapper
 * @Author fengwensheng
 * @Date 2024/02/21
 * @Version V1.0
 **/
public interface MemberCartMapper extends BaseMapper<MemberCart> {
    List<MemberCartVO> listMemberCart(@Param(value = "query") MemberCart memberCart);
    List<MemberCartVO> listMemberCartByIds(List<Integer> memberCartIds);
}
