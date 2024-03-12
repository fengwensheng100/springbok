package cn.code4java.springbok.storage;

import java.io.InputStream;

/**
 * @ClassName Storage
 * @Description: 对象存储接口
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public interface Storage {
    /**
     * 存储一个对象
     *
     * @param inputStream
     * @param contentType 文件类型
     * @param keyName     文件名
     * @param type        分区类型
     */
    void store(InputStream inputStream, String contentType, String keyName, int type);

    String generateUrl(String keyName, int type);
}
