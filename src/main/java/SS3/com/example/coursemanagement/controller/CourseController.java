package SS3.com.example.coursemanagement.controller;

import SS3.com.example.coursemanagement.exception.ResourceNotFoundException;
import SS3.com.example.coursemanagement.model.ApiResponse;
import SS3.com.example.coursemanagement.model.Course;
import SS3.com.example.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        List<Course> list = courseService.getAllCourses();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thành công!", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(ApiResponse.success("Tìm thấy thông tin khóa học!", course));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course newCourse) {
        try {
            Course created = courseService.createCourse(newCourse);
            return new ResponseEntity<>(ApiResponse.success("Tạo khóa học thành công!", created), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Long id, @RequestBody Course updatedData) {
        try {
            Course updated = courseService.updateCourse(id, updatedData);
            return ResponseEntity.ok(ApiResponse.success("Cập nhật khóa học thành công!", updated));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable Long id) {
        try {
            Course deleted = courseService.deleteCourseById(id);
            return ResponseEntity.ok(ApiResponse.success("Xóa khóa học thành công!", deleted));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }
}