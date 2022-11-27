package com.spring.ecommerce2.service;

import com.spring.ecommerce2.FileStorageProperties;
import com.spring.ecommerce2.exception.FileNotFoundException;
import com.spring.ecommerce2.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties prop) {
        this.fileStorageLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException("Failed to create directory", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String filenameExtension = StringUtils
                    .getFilenameExtension(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            String fileName = UUID.randomUUID() + "." +filenameExtension;
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException("Failed to save file", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName);
            UrlResource resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) {
                throw new FileNotFoundException("File not found!");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found!", e);
        }
    }
}