package SS5.course.service;

import SS5.course.dto.response.CourseResponseV2;
import SS5.course.model.Course;
import SS5.course.model.CourseStatus;
import SS5.course.dto.CourseMapper;
import SS5.course.repository.CourseRepository;
import SS5.course.dto.response.CourseResponse;
import SS5.course.dto.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(
            int page, int size, String sortBy, Sort.Direction direction,
            CourseStatus status) { // Đã sửa: bổ sung kiểu dữ liệu CourseStatus

        // 1. Safety check cho tham số page
        if (page < 0) {
            page = 0;
        }

        // 2. Xử lý logic sắp xếp mặc định
        String sortProperty = (sortBy == null || sortBy.trim().isEmpty()) ? "id" : sortBy;
        Sort sort = Sort.by(direction, sortProperty);

        // 3. Tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // 4. Gọi Repository mới với câu lệnh JPQL và truyền tham số status
        Page<Course> coursePage = courseRepository.findAllByStatus(status, pageable);

        // 5. Map Page<Course> -> Page<CourseResponse>
        Page<CourseResponse> responsePage = coursePage.map(course -> courseMapper.toCourseResponse(course));

        // 6. Đóng gói vào PageResponse Wrapper giống Bài 2
        return new PageResponse<>(
                responsePage.getContent(),
                responsePage.getNumber(),
                responsePage.getSize(),
                responsePage.getTotalElements(),
                responsePage.getTotalPages(),
                responsePage.isLast()
        );
    }

    public PageResponse<CourseResponseV2> getPagedCoursesByStatusV2(
            int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {

        // 1. Safety check cho page
        if (page < 0) {
            page = 0;
        }

        // 2. Cấu hình Sort mặc định
        String sortProperty = (sortBy == null || sortBy.trim().isEmpty()) ? "id" : sortBy;
        Sort sort = Sort.by(direction, sortProperty);

        // 3. Tạo Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // 4. Gọi Repository, dữ liệu trả về ĐÃ LÀ CourseResponseV2 sẵn rồi
        Page<CourseResponseV2> projectionPage = courseRepository.findAllByStatusProjection(status, pageable);

        // 5. Đóng gói thẳng vào PageResponse và trả về
        return new PageResponse<>(
                projectionPage.getContent(),
                projectionPage.getNumber(),
                projectionPage.getSize(),
                projectionPage.getTotalElements(),
                projectionPage.getTotalPages(),
                projectionPage.isLast()
        );
    }
}