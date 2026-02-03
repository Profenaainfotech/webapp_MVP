package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.*;
import com.example.Profenaa_touch.entity.*;
import com.example.Profenaa_touch.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/course")
public class AdminTopicController {

    private final TopicRepository topicRepo;
    private final CourseRepository courseRepo;
    private final VideoStorageService videoService;
    private final MaterialStorageService materialService;

    public AdminTopicController(
            TopicRepository t,
            CourseRepository c,
            VideoStorageService v,
            MaterialStorageService m
    ) {
        topicRepo = t;
        courseRepo = c;
        videoService = v;
        materialService = m;
    }

    @PostMapping("/{courseId}/topic")
    public Topic uploadTopic(
            @PathVariable Long courseId,
            @RequestParam String title,
            @RequestParam Integer duration,
            @RequestParam MultipartFile video,
            @RequestParam(required = false) MultipartFile material
    ) throws Exception {

        Course course = courseRepo.findById(courseId).orElseThrow();

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setDuration(duration);
        topic.setVideoUrl(videoService.save(video));
        topic.setMaterialUrl(materialService.save(material));
        topic.setCourse(course);

        return topicRepo.save(topic);
    }
}
