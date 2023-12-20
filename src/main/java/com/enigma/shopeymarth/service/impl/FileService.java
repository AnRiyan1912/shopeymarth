package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.entity.Files;
import com.enigma.shopeymarth.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final String FILE_PATH = "/home/user/BATCH14/spring boot/shopeymarth/src/main/java/com/enigma/shopeymarth/images/";

    @Transactional
    public byte[] getFiles(String fileName) {
        return fileRepository.findByName(fileName).getImageData();
    }
    public String storeDataIntoFileSystem(MultipartFile file) throws IOException{
        String filePath = FILE_PATH + file.getOriginalFilename();

        Files files = Files.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .path(filePath)
                .imageData(file.getBytes())
                .build();

        file.transferTo(new File(filePath));

        files = fileRepository.save(files);
        if (files.getId() != null) {
            return "File uploaded successfully into database";
        }
        return null;
    }
    public byte[] downloadFileFromFIleSystem(String fileName) throws IOException {
        String path = fileRepository.findByName(fileName).getPath();
        return java.nio.file.Files.readAllBytes(new File(path).toPath());
    }
}
