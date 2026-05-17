package SS4.course_management.controller;

import SS4.course_management.dto.ApiResponse;
import SS4.course_management.dto.request.CourseCreateRequest;
import SS4.course_management.dto.request.CourseUpdateRequest;
import SS4.course_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.CREATED.value())
                .message("Tạo khóa học thành công!")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Cập nhật thông tin khóa học thành công!")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}