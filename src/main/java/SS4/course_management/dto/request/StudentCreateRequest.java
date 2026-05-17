package SS4.course_management.dto.request;


import lombok.Data;

@Data
public class StudentCreateRequest {
    private String name;
    private String email;
}