package SS5.course.dto.response;


import SS5.course.model.CourseStatus;

public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;

    // Không tham số (Jackson cần khi serialize sang JSON)
    public CourseResponseV2() {
    }

    // Constructor đầy đủ tham số - BẮT BUỘC phải có để JPQL map dữ liệu
    public CourseResponseV2(Long id, String title, CourseStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    // Getters và Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public CourseStatus getStatus() { return status; }
    public void setStatus(CourseStatus status) { this.status = status; }
}