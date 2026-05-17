package SS4.course_management.controller;


import SS4.course_management.dto.ApiResponse;
import SS4.course_management.dto.request.InstructorCreateRequest;
import SS4.course_management.model.Instructor;
import SS4.course_management.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> getAll() {
        return ResponseEntity.ok(instructorService.findAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.findInstructorById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.CREATED.value())
                .message("Tạo giảng viên thành công!")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}