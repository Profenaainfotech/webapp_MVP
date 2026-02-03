package com.example.Profenaa_touch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@Service
public class MaterialStorageService {

    private static final String DIR = "uploads/materials/";

    public String save(MultipartFile file) throws Exception {
        if (file == null) return null;

        Files.createDirectories(Paths.get(DIR));

        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(DIR + name));

        return "/materials/" + name;
    }
}
