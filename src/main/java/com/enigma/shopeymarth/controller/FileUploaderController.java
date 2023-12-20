package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileUploaderController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<String> uploadFileIntoFileSystem(@RequestParam("file")MultipartFile file) throws IOException{
        String response = fileService.storeDataIntoFileSystem(file);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{name}")
    public ResponseEntity<?> getFileFromFileSystem(@PathVariable String name){
        byte[] imageData = fileService.getFiles(name);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
