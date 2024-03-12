package cn.code4java.springbok.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @ClassName ApiProperties
 * @Description: ApiProperties
 * @Author fengwensheng
 * @Date 2024/2/23
 * @Version V1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "springbok.auth")
public class AuthProperties {
    /**
     * 放行url
     */
    private List<String> notMatchUrls;
}
