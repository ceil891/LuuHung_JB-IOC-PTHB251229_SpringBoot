package SS5.course.dto.response;


public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    // Bạn có thể thêm các trường khác tùy thuộc vào bài tập trước của bạn, ví dụ:
    // private String authorName;
    // private Double price;

    // Constructor không tham số
    public CourseResponse() {
    }

    // Constructor đầy đủ tham số
    public CourseResponse(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters và Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}