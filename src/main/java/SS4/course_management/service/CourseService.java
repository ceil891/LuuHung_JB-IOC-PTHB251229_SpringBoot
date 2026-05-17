package SS4.course_management.service;


import SS4.course_management.dto.request.CourseCreateRequest;
import SS4.course_management.dto.request.CourseUpdateRequest;
import SS4.course_management.model.Course;
import SS4.course_management.model.Instructor;
import SS4.course_management.repository.CourseRepository;
import SS4.course_management.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public void createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + req.getInstructorId()));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor); // Set trực tiếp đối tượng mapping

        courseRepository.save(course);
    }

    // Cập nhật khóa học
    public void updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với ID: " + id));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + req.getInstructorId()));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }
}