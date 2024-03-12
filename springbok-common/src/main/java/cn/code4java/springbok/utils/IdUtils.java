package cn.code4java.springbok.utils;

import cn.code4java.springbok.enums.BillTypeEnum;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;

import java.util.*;

/**
 * @ClassName IdUtils
 * @Description: TODO
 * @Author fengwensheng
 * @Date 2023/11/25
 * @Version V1.0
 **/
public class IdUtils {

    public static String generateOrderNo() {
        String orderNo = System.currentTimeMillis() + String.valueOf((int) (Math.random() * 10)) + (int) (Math.random() * 10) + (int) (Math.random() * 10);
        return orderNo;
    }

    public static String generateBillNo(BillTypeEnum billTypeEnum) {
        String billNo = billTypeEnum.getCode() + System.currentTimeMillis()
                + String.valueOf((int) (Math.random() * 10))
                + (int) (Math.random() * 10) + (int) (Math.random() * 10);
        return billNo;
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        String body = "{\"memberId\":1,\"address\":\"广州市越秀区北京路步行街\",\"receiver\":\"张三\"}";
        if (StringUtils.isNotBlank(body)) {
            Map<String, Object> paramsMap = JSONUtil.toBean(body, HashMap.class);
            List<String> list = new ArrayList<>(paramsMap.keySet());
            Collections.sort(list);
            for (String key : list) {
                stringBuffer.append(key + "=" + paramsMap.get(key) + "&");
            }
        }
        stringBuffer.append("timestamp=" + System.currentTimeMillis());
        stringBuffer.append("&");
        stringBuffer.append("appSecret=" + "123456");
        String signStr = MD5.create().digestHex(stringBuffer.toString());
        System.out.println(signStr);
    }
}
