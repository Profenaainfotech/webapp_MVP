package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.*;
import com.example.Profenaa_touch.entity.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final TopicRepository topicRepo;
    private final EnrollmentRepository enrollRepo;
    private final UserRepository userRepo;

    public VideoController(
            TopicRepository topicRepo,
            EnrollmentRepository enrollRepo,
            UserRepository userRepo
    ) {
        this.topicRepo = topicRepo;
        this.enrollRepo = enrollRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/stream/{topicId}", produces = "video/mp4")
    public ResponseEntity<Resource> streamVideo(
            @PathVariable Long topicId,
            Authentication auth
    ) {

        // 1️⃣ User
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();

        // 2️⃣ Topic
        Topic topic = topicRepo.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Course course = topic.getCourse();

        // 3️⃣ Enrollment check
        if (!enrollRepo.existsByUserAndCourse(user, course)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // 4️⃣ Video file
        File file = new File(topic.getVideoUrl());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .body(resource);
    }
}
