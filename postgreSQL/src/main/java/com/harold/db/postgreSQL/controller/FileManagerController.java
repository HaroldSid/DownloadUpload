package com.harold.db.postgreSQL.controller;

import com.harold.db.postgreSQL.model.FileH;
import com.harold.db.postgreSQL.repository.FileManagerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/fileH")
public class FileManagerController {

    private final FileManagerRepository fileManagerRepository;

    public FileManagerController(FileManagerRepository fileManagerRepository) {
        this.fileManagerRepository = fileManagerRepository;
    }

    @GetMapping
    public ResponseEntity getAllFile() {
        return ResponseEntity.ok(this.fileManagerRepository.findAll());
    }

    @PostMapping
    public FileH createFile(@RequestPart("fileH") MultipartFile multipartFile) throws IOException {
        FileH newFile = new FileH();

        newFile.setId(UUID.randomUUID().toString());
        newFile.setFileName(multipartFile.getOriginalFilename());
        newFile.setFileType(multipartFile.getContentType());
        newFile.setFileData(multipartFile.getBytes());

        fileManagerRepository.save(newFile);

        return newFile;
    }
}
