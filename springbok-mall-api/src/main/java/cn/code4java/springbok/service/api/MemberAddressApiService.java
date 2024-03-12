package cn.code4java.springbok.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.code4java.springbok.entity.MemberAddress;

/**
 * @ClassName MemberAddressApiService
 * @Description: 会员地址API服务类
 * @Author fengwensheng
 * @Date 2024/1/27
 * @Version V1.0
 **/
public interface MemberAddressApiService extends IService<MemberAddress> {
    /**
     * 新增会员地址
     *
     * @param memberAddress
     * @return
     */
    int addMemberAddress(MemberAddress memberAddress);

    /**
     * 修改会员地址
     *
     * @param memberAddress
     * @return
     */
    int updateMemberAddress(MemberAddress memberAddress);

    /**
     * 删除会员地址
     *
     * @param memberAddressId
     * @return
     */
    int deleteMemberAddress(Integer memberAddressId);
}
