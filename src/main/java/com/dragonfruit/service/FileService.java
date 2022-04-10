package com.dragonfruit.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    public void save(String title, String description, MultipartFile file);
    public byte[] downloadImage(String imagePath, String filename);
}
