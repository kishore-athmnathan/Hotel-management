package com.phegondev.HotelPhegon.service;

import com.phegondev.HotelPhegon.exception.OurException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageStorageService {

    private final String uploadDir = "uploads";

    public String saveImageLocally(MultipartFile image) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File targetFile = new File(dir, fileName);
            image.transferTo(targetFile);

            // Return actual path where Spring serves it from
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new OurException("Failed to upload image locally");
        }
    }
}

