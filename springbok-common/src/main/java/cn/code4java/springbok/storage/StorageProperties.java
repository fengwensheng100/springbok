package cn.code4java.springbok.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName StorageProperties
 * @Description: StorageProperties
 * @Author fengwensheng
 * @Date 2024/1/8
 * @Version V1.0
 **/
@Data
@ConfigurationProperties(prefix = "springbok.storage")
public class StorageProperties {
    private String active;
    private Local local;
    private Aliyun aliyun;
    private Tencent tencent;

    @Data
    public static class Local {
        private String address;
        private String storagePath;
    }

    @Data
    public static class Aliyun {
        private String accessKeyId;
        private String accessKeySecret;
    }

    @Data
    public static class Tencent {
        private String appId;
        private String secretId;
        private String secretKey;
    }
}
