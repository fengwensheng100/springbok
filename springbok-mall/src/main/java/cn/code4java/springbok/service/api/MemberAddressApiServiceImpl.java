package cn.code4java.springbok.service.api;

import cn.code4java.springbok.mapper.MemberAddressMapper;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.code4java.springbok.entity.MemberAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberAddressApiServiceImpl
 * @Description: 会员地址API服务实现类
 * @Author fengwensheng
 * @Date 2023/11/22
 * @Version V1.0
 **/
@Service
@AllArgsConstructor
public class MemberAddressApiServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressApiService {

    private MemberAddressMapper memberAddressMapper;

    /**
     * 新增会员地址
     *
     * @param memberAddress
     * @return
     */
    @Override
    public int addMemberAddress(MemberAddress memberAddress) {
        if (memberAddress.getDefaultAddress()) {
            MemberAddress updateMemberAddress = new MemberAddress();
            updateMemberAddress.setDefaultAddress(false);
            this.update(updateMemberAddress, new LambdaQueryWrapper<MemberAddress>()
                    .eq(MemberAddress::getMemberId, StpUtil.getLoginIdAsInt())
                    .eq(MemberAddress::getDefaultAddress, true));
        }
        memberAddress.setMemberId(StpUtil.getLoginIdAsInt());
        return memberAddressMapper.insert(memberAddress);
    }

    /**
     * 修改会员地址
     *
     * @param memberAddress
     * @return
     */
    @Override
    public int updateMemberAddress(MemberAddress memberAddress) {
        if (memberAddress.getDefaultAddress()) {
            MemberAddress updateMemberAddress = new MemberAddress();
            updateMemberAddress.setDefaultAddress(false);
            this.update(updateMemberAddress, new LambdaQueryWrapper<MemberAddress>()
                    .eq(MemberAddress::getMemberId, StpUtil.getLoginIdAsInt())
                    .eq(MemberAddress::getDefaultAddress, true));
        }
        return memberAddressMapper.updateById(memberAddress);
    }

    /**
     * 删除会员地址
     *
     * @param memberAddressId
     * @return
     */
    @Override
    public int deleteMemberAddress(Integer memberAddressId) {
        return memberAddressMapper.deleteById(memberAddressId);
    }
}
