package com.baikati.file.operation.controller;

import com.baikati.file.operation.response.Response;
import com.baikati.file.operation.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class FileController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("FileController.uploadFile() {} ",file.getOriginalFilename());
        String fileName = storageService.storeFile(file);

        String downloadFileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, downloadFileUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        log.info("FileController.uploadMultipleFiles() ");
        return Arrays.asList(files).stream()
                .map(file -> uploadFile(file)).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        String contentType = "";
        Resource resource = null;
        try {
            resource = storageService.loadFile(fileName);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (FileNotFoundException fne) {
            log.info("file not found " + fne);
        } catch (IOException ioe) {
            log.info("Could not determine file type.");
        }
        if (contentType.isBlank() && contentType.isEmpty()) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
