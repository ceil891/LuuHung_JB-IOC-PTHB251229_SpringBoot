package SS4.course_management.controller;

import SS4.course_management.dto.ApiResponse;
import SS4.course_management.dto.request.EnrollmentRequest;
import SS4.course_management.service.StudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students-enrollments")
@RequiredArgsConstructor
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enroll(@RequestBody EnrollmentRequest req) {
        studentEnrollmentService.enrollStudent(req.getStudentId(), req.getCourseId());
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Đăng ký sinh viên vào khóa học thành công!")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}