package com.dragonfruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dragonfruit.service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/image")
@AllArgsConstructor
@CrossOrigin("*")
public class ImageController {
	
	@Autowired
	private FileService fileService;
	
    @PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void saveTodo(@RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam("file") MultipartFile file) {
    	fileService.save(title, description, file);
    }

    @GetMapping(value = "/download")
    public byte[] downloadImage(@RequestParam String imagePath, @RequestParam String filename) {
        return fileService.downloadImage(imagePath, filename);
    }	

}
