package com.example.lifeos.controller;

import com.example.lifeos.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final ImageService imageService;


    @GetMapping("/{id}")
    public ResponseEntity<Resource> findFile(@PathVariable String id){
        InputStream inputStream = imageService.findFile(id);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file\"")
                .body(resource);
    }

    @PostMapping
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(imageService.saveFile(multipartFile));
    }


}
