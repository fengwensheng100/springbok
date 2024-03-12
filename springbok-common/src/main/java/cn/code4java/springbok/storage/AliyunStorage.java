package cn.code4java.springbok.storage;

import cn.code4java.springbok.enums.OSSDistrictEnum;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @ClassName AliyunStorage
 * @Description: 阿里云OSS
 * @Author fengwensheng
 * @Date 2024/03/11
 * @Version V1.0
 **/
@Slf4j
@Data
public class AliyunStorage implements Storage {

    private String bucketName = "springbok";
    private String endpoint = "oss-cn-guangzhou.aliyuncs.com";
    private String accessKeyId;
    private String accessKeySecret;

    @Override
    public void store(InputStream inputStream, String contentType, String keyName, int type) {
        OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
        // 创建bucket
        createBucket(bucketName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, ossDistrictEnum.getValue() + "/" + keyName, inputStream, objectMetadata);
        getOSSClient().putObject(putObjectRequest);
    }

    @Override
    public String generateUrl(String keyName, int type) {
        OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
        return "https://" + bucketName + "." + endpoint + "/" + ossDistrictEnum.getValue() + "/" + keyName;
    }

    /**
     * 创建bucket
     */
    private void createBucket(String bucketName) {
        OSS ossClient = getOSSClient();
        try {
            // 创建存储空间
            boolean exists = ossClient.doesBucketExist(bucketName);
            if (!exists) {
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                // 设置存储空间读写权限为公共读，默认为私有
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 创建OSS实例
     *
     * @return
     */
    private OSS getOSSClient() {
        return new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessKeySecret);
    }
}
