package SS7.Candidate.controller;



import SS7.Candidate.model.ApiResponse;
import SS7.Candidate.model.dto.CandidateCreateDTO;
import SS7.Candidate.model.dto.CandidateUpdateDTO;
import SS7.Candidate.model.enitity.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final List<Candidate> candidateList = new ArrayList<>();
    private final AtomicInteger autoId = new AtomicInteger(1);

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setId(autoId.getAndIncrement());
        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setAge(dto.getAge());
        candidate.setYearsOfExperience(dto.getYearsOfExperience());
        candidate.setPhone(dto.getPhone());

        candidateList.add(candidate);

        return ResponseEntity.ok(candidate);
    }
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<?> updateCandidate(
            @PathVariable Integer id,
            @Valid @ModelAttribute CandidateUpdateDTO dto) {

        Candidate existingCandidate = candidateList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingCandidate == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", "Không tìm thấy ứng viên với ID: " + id, null));
        }

        existingCandidate.setAddress(dto.getAddress());
        existingCandidate.setBio(dto.getBio());

        return ResponseEntity.ok(new ApiResponse<>("success", "Cập nhật hồ sơ thành công!", existingCandidate));
    }
}