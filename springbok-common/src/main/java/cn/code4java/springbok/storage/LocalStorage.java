package cn.code4java.springbok.storage;

import cn.code4java.springbok.enums.OSSDistrictEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ClassName LocalStorage
 * @Description: 本地存储
 * @Author fengwensheng
 * @Date 2023/12/23
 * @Version V1.0
 **/
@Slf4j
@Data
public class LocalStorage implements Storage {

    private Path rootLocation;
    private String address;
    private String storagePath;

    @Override
    public void store(InputStream inputStream, String contentType, String keyName, int type) {
        try {
            OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
            this.rootLocation = Paths.get(storagePath + "/" + ossDistrictEnum.getValue());
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            Files.copy(inputStream, rootLocation.resolve(keyName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + keyName, e);
        }
    }

    @Override
    public String generateUrl(String keyName, int type) {
        OSSDistrictEnum ossDistrictEnum = OSSDistrictEnum.getOSSDistrictEnum(type);
        return address + ossDistrictEnum.getValue() + "/" + keyName;
    }
}
