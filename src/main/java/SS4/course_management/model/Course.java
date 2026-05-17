package SS4.course_management.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status;

    // Quan hệ N-1: Nhiều Khóa học do 1 Giảng viên dạy
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Quan hệ 1-N với bảng trung gian
    @OneToMany(mappedBy = "course")
    private List<StudentEnrollment> enrollments = new ArrayList<>();
}