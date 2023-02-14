package com.harold.db.postgreSQL.controller;

import com.harold.db.postgreSQL.model.FileH;
import com.harold.db.postgreSQL.repository.FileManagerRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("downloadFile")
public class DownloadFileController {

    private final FileManagerRepository fileManagerRepository;

    public DownloadFileController(FileManagerRepository fileManagerRepository) {
        this.fileManagerRepository = fileManagerRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte []> download(@PathVariable String id) {
        FileH fileH = fileManagerRepository.getReferenceById(id);
        if (fileH == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            byte[] fileData = fileH.getFileData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(fileH.getFileType()));
            ContentDisposition build = ContentDisposition
                    .builder("inline")
                    .filename(fileH.getFileName())
                    .build();
            headers.setContentDisposition(build);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            return responseEntity;
        }
    }
}
