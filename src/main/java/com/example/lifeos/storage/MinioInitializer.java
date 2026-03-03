package com.example.lifeos.storage;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MinioInitializer {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    @PostConstruct
    public void bucketSetup(){
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .build()
            );

            if (!exists){
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(minioProperties.getBucket())
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bucket", e);
        }
    }

}
