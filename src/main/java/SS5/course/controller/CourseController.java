package SS5.course.controller;

import SS5.course.dto.response.ApiResponse;
import SS5.course.dto.response.CourseResponseV2;
import SS5.course.model.CourseStatus;
import SS5.course.service.CourseService;
import SS5.course.dto.response.CourseResponse;
import SS5.course.dto.response.PageResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCourses(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "status", required = false, defaultValue = "ACTIVE") CourseStatus status // Thêm nhận param status
    ) {
        // Đã sửa: gọi đúng hàm getPagedCoursesByStatus và truyền thêm biến status
        PageResponse<CourseResponse> pagedCourses = courseService.getPagedCoursesByStatus(page, size, sortBy, direction, status);

        // Bao bọc dữ liệu trong ApiResponse
        ApiResponse<PageResponse<CourseResponse>> apiResponse = new ApiResponse<>(
                "Success",
                "Fetch courses successfully",
                pagedCourses
        );

        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/v2")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getCoursesV2(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "status", required = false, defaultValue = "ACTIVE") CourseStatus status
    ) {
        // Gọi hàm Service phiên bản V2 dùng Projection
        PageResponse<CourseResponseV2> pagedCourses = courseService.getPagedCoursesByStatusV2(page, size, sortBy, direction, status);

        ApiResponse<PageResponse<CourseResponseV2>> apiResponse = new ApiResponse<>(
                "Success",
                "Fetch courses with projection successfully",
                pagedCourses
        );

        return ResponseEntity.ok(apiResponse);
    }
}