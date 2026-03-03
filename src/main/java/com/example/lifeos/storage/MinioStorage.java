package com.example.lifeos.storage;

import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioStorage implements FileStorage{
    private final MinioProperties minioProperties;
    private final MinioClient minioClient;


    @Override
    public String upload(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();

        try{
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(uuid)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Fileupload failed.", e);
        }
        return uuid;
    }

    @Override
    public InputStream load(String id) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(id)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }

}
