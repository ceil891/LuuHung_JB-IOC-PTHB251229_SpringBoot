package SS3.com.example.coursemanagement.repository;


import SS3.com.example.coursemanagement.model.Instructor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1L, "Dr. Nguyễn Lưu Hưng", "hung.nguyen@university.edu"));
        instructors.add(new Instructor(2L, "Prof. Le Thi Thùy", "thuy.le@university.edu"));
    }

    public List<Instructor> findAll() {
        return instructors;
    }
}