package SS4.course_management.service;


import SS4.course_management.dto.request.InstructorCreateRequest;
import SS4.course_management.model.Instructor;
import SS4.course_management.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;


    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor findInstructorById(Long id) {

        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + id));
    }

    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }
}