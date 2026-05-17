package SS2.com.example.taskmanagement.model;
public class Task {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private Long assignedTo; // Chứa ID của User (Foreign Key logic)

    // Constructor không tham số
    public Task() {}

    // Constructor có tham số
    public Task(Long id, String title, String description, String priority, Long assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignedTo = assignedTo;
    }

    // Getters và Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }
}