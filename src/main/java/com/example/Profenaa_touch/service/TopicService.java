package com.example.Profenaa_touch.service;

import com.example.Profenaa_touch.Repository.CourseRepository;
import com.example.Profenaa_touch.Repository.TopicRepository;
import com.example.Profenaa_touch.entity.Course;
import com.example.Profenaa_touch.entity.Topic;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepo;
    private final CourseRepository courseRepo;
    private final VideoStorageService videoStorage;
    private final MaterialStorageService materialStorage;

    public TopicService(TopicRepository topicRepo,
                        CourseRepository courseRepo,
                        VideoStorageService videoStorage,
                        MaterialStorageService materialStorage) {
        this.topicRepo = topicRepo;
        this.courseRepo = courseRepo;
        this.videoStorage = videoStorage;
        this.materialStorage = materialStorage;
    }

    // ADMIN → upload topic
    public Topic addTopic(Long courseId,
                          String title,
                          Integer duration,
                          MultipartFile video,
                          MultipartFile material) throws Exception {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setDuration(duration);
        topic.setVideoUrl(videoStorage.save(video));
        topic.setMaterialUrl(materialStorage.save(material));
        topic.setCourse(course);

        return topicRepo.save(topic);
    }

    // USER → get topics of a course
    public List<Topic> getTopicsByCourse(Long courseId) {
        return topicRepo.findByCourseId(courseId);
    }
}
