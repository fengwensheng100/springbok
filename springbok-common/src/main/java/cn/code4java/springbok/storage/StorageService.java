package cn.code4java.springbok.storage;

import cn.hutool.core.lang.UUID;

import java.io.InputStream;

/**
 * @ClassName StorageService
 * @Description: 存储服务类
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
public class StorageService {

    private String active;

    private Storage storage;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     * @param fileName    文件索引名
     * @param type        分区类型
     */
    public SystemStorage store(InputStream inputStream, String contentType, String fileName, int type) {
        String key = generateKey(fileName);
        storage.store(inputStream, contentType, key, type);
        String url = generateUrl(key, type);
        SystemStorage storageInfo = new SystemStorage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) 0);
        storageInfo.setType(contentType);
        storageInfo.setKey(key);
        storageInfo.setUrl(url);
        return storageInfo;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        String key = null;
        SystemStorage storageInfo = null;
        do {
            key = UUID.randomUUID() + suffix;
        }
        while (storageInfo != null);
        return key;
    }

    private String generateUrl(String keyName, int type) {
        return storage.generateUrl(keyName, type);
    }
}
