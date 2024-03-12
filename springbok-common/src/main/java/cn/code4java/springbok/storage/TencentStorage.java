package cn.code4java.springbok.storage;

import cn.code4java.springbok.enums.OSSDistrictEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @ClassName TencentStorage
 * @Description: 腾讯云COS
 * @Author fengwensheng
 * @Date 2024/03/12
 * @Version V1.0
 **/
@Slf4j
@Data
public class TencentStorage implements Storage {

    private String bucketName = "springbok";
    private String endpoint = "ap-guangzhou";
    private String appId;
    private String secretId;
    private String secretKey;

    @Override
    public void store(InputStream inputStream, String contentType, String keyName, int type) {
        OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
        // 创建bucket
        createBucket(bucketName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, ossDistrictEnum.getValue() + "/" + keyName, inputStream, objectMetadata);
        getCOSClient().putObject(putObjectRequest);
    }

    @Override
    public String generateUrl(String keyName, int type) {
        OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
        return "https://" + bucketName + ".cos." + endpoint + ".myqcloud.com/" + ossDistrictEnum.getValue() + "/" + keyName;
    }

    /**
     * 创建bucket
     */
    private void createBucket(String bucketName) {
        COSClient cosClient = getCOSClient();
        try {
            // 创建存储空间
            boolean exists = cosClient.doesBucketExist(bucketName);
            if (!exists) {
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
                createBucketRequest.setCannedAcl(CannedAccessControlList.PublicRead);
                cosClient.createBucket(createBucketRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
    }

    /**
     * 创建COS实例
     *
     * @return
     */
    private COSClient getCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(endpoint);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
}
