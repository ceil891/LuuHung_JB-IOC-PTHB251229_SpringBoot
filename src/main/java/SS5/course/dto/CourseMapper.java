package SS5.course.dto;

import SS5.course.model.Course;
import SS5.course.dto.response.CourseResponse;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toCourseResponse(Course course) {
        if (course == null) {
            return null;
        }

        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());

        return response;
    }
}