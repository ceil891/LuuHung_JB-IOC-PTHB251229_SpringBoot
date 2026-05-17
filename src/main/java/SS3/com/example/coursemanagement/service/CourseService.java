package SS3.com.example.coursemanagement.service;

import SS3.com.example.coursemanagement.model.Course;
import SS3.com.example.coursemanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course newCourse) {
        if (newCourse.getId() == null || getCourseById(newCourse.getId()) != null) {
            return null;
        }
        return courseRepository.create(newCourse);
    }

    public Course updateCourse(Long id, Course updatedData) {
        return courseRepository.update(id, updatedData);
    }

    public Course deleteCourseById(Long id) {
        return courseRepository.deleteById(id);
    }
}