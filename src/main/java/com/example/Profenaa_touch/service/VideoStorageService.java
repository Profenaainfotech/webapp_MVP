package com.example.Profenaa_touch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@Service
public class VideoStorageService {

    private static final String DIR = "uploads/videos/";
    private static final long MAX_SIZE = 100 * 1024 * 1024;

    public String save(MultipartFile file) throws Exception {

        if (file.getSize() > MAX_SIZE)
            throw new RuntimeException("Video > 100MB");

        Files.createDirectories(Paths.get(DIR));

        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(DIR + name));

        return "/videos/" + name;
    }
}
