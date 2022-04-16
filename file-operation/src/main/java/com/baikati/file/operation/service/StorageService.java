package com.baikati.file.operation.service;

import com.baikati.file.operation.config.StorageProperties;
import com.baikati.file.operation.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {
    private Path location;

    @Autowired
    public StorageService(StorageProperties properties) throws FileStorageException {
        this.location = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.location);

        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("file name contains invalid path " + fileName);
            }
            Path targetLocation = this.location.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ioe) {
            throw new FileStorageException("unable to store file " + fileName + " try again...");
        }

    }

    public Resource loadFile(String fileName) throws FileNotFoundException {
        try {
            Path filePath = this.location.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
            throw new FileNotFoundException("file not found " + fileName);
        }
        } catch (MalformedURLException exception) {
            throw new FileNotFoundException("file not found " + fileName);
        }
    }
}
