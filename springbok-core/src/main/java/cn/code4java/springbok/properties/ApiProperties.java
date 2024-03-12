package cn.code4java.springbok.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ApiProperties
 * @Description: ApiProperties
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "springbok.api")
public class ApiProperties {
    /**
     * 安全性配置
     */
    private Security security;
    /**
     * 幂等配置
     */
    private Idempotent idempotent;

    @Data
    public static class Security {
        /**
         * 请求有效时间
         */
        private Long expireTime;
    }

    @Data
    public static class Idempotent {
        /**
         * 幂等key前缀
         */
        private String keyPrefix = "idempotent";
    }
}
