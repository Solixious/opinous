package com.opinous.service.impl;

import com.opinous.exception.FileStorageException;
import com.opinous.exception.MyFileNotFoundException;
import com.opinous.service.FileStorageService;
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

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private Path fileStorageLocation;

    public FileStorageServiceImpl() throws FileStorageException {
        super();
        this.fileStorageLocation = Paths.get("./img")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception e) {
            throw new FileStorageException("Could not create the directory where " +
                    "file will be stored", e);
        }
    }
    public String storeFile(MultipartFile file, String suggestedFileName) throws FileStorageException {
        String fileName = StringUtils.cleanPath(suggestedFileName);
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("File name contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch(IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) throws MyFileNotFoundException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else
                throw new MyFileNotFoundException("File not found " + fileName);
        } catch(MalformedURLException e) {
            throw new MyFileNotFoundException("File not found " + fileName, e);
        }
    }
}
