package SS4.course_management.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ N-1 với Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Quan hệ N-1 với Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}