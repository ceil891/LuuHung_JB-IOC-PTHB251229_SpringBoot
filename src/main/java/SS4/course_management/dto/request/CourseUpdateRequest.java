package SS4.course_management.dto.request;


import SS4.course_management.model.CourseStatus;
import lombok.Data;

@Data
public class CourseUpdateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
