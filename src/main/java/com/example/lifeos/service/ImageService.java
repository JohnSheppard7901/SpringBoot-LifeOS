package com.example.lifeos.service;

import com.example.lifeos.storage.FileStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final FileStorage fileStorage;

    private static final List<String> ALLOWED_CONTENT_TYPES = List.of(
      "image/jpeg",
      "image/png",
      "image/gif",
      "image/webp"
    );

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024;     // 20 MB

    public InputStream findFile(String id){
       return fileStorage.load(id);
    }

    public String saveFile(MultipartFile multipartFile){
        validateFile(multipartFile);
        return fileStorage.upload(multipartFile);
    }

    private void validateFile(MultipartFile file){
        // 1. ist leer
        // 2. max size überschritten
        // 3. content type nicht erlaubt

        if (file.getSize() > MAX_FILE_SIZE)
            throw new IllegalArgumentException("File size exceeded max.");

        if(file.isEmpty())
            throw new IllegalArgumentException("File can not be empty.");

        if (file.getContentType() == null || !ALLOWED_CONTENT_TYPES.contains(file.getContentType()))
            throw new IllegalArgumentException("Content type of file not allowed. Only jpeg, png, gif and webp allowed.");
    }

}
