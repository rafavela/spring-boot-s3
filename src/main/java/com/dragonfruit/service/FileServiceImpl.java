package com.dragonfruit.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dragonfruit.components.S3Settings;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileStore fileStore;
	
	@Autowired
	private S3Settings s3Settings;
	
	@Override
    public void save(String title, String description, MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        System.out.println("content type "+file.getContentType());
//        if (!Arrays.asList(IMAGE_PNG,IMAGE_GIF,IMAGE_JPEG)
//        		.contains(file.getContentType())) {
//            throw new IllegalStateException("FIle uploaded is not an image");
//        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", 
        		s3Settings.getBucketName(), 
        		UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }

	    @Override
	    public byte[] downloadImage(String imagePath, String filename) {
	        return fileStore.download(imagePath, filename);
	    }
	
}
