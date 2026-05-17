package SS3.com.example.coursemanagement.repository;


import SS3.com.example.coursemanagement.exception.ResourceNotFoundException;
import SS3.com.example.coursemanagement.model.Course;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(101L, "Java BootCamp", "ACTIVE", 1L)); // Giảng viên 1 dạy
        courses.add(new Course(102L, "Web Development", "ACTIVE", 2L)); // Giảng viên 2 dạy
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course newCourse) {
        courses.add(newCourse);
        return newCourse;
    }


    public Course update(Long id, Course updatedData) {
        Course currentCourse = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học ID " + id + " để cập nhật."));

        currentCourse.setTitle(updatedData.getTitle());
        currentCourse.setStatus(updatedData.getStatus());
        currentCourse.setInstructorId(updatedData.getInstructorId());
        return currentCourse;
    }

    public Course deleteById(Long id) {
        Course target = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học ID " + id + " để xóa."));

        courses.remove(target);
        return target;
    }

}