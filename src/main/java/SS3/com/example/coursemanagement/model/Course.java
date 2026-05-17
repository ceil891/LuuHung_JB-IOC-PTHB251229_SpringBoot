package SS3.com.example.coursemanagement.model;


public class Course {
    private Long id;
    private String title;
    private String status; // Ví dụ: "ACTIVE", "PENDING"
    private Long instructorId; // Liên kết tới Instructor (1-N)

    public Course() {}

    public Course(Long id, String title, String status, Long instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    // Getters và Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
}