package com.example.lifeos.storage.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MinioProperties {
    @Value("${minio.url}")
    private String url;

    @Value("${minio.port}")
    private int port;

    @Value("${minio.user}")
    private String user;

    @Value("${minio.password}")
    private String password;

    @Value("${minio.bucket}")
    private String bucket;


}
