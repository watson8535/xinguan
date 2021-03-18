package com.edward.system.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里oss实体类
 * 需要将这个类交给spring管理 @componet
 */
@ConfigurationProperties(prefix = "alioss") //获取yml里alioss的内容
@Component
@Data
public class OssEntity {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
