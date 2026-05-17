package SS3.com.example.coursemanagement.repository;


import SS3.com.example.coursemanagement.model.Enrollment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1001L, "Tran Van Tuấn", 101L));
        enrollments.add(new Enrollment(1002L, "Pham Thi Dung", 102L));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }
}