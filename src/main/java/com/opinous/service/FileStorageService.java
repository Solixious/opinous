package com.opinous.service;

import com.opinous.exception.FileStorageException;
import com.opinous.exception.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileStorageService {

	String storeFile(MultipartFile file, String suggestedFileName) throws FileStorageException;

	Resource loadFileAsResource(String fileName) throws MyFileNotFoundException;
}
