package SS7.Candidate.model.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private Integer id;
    private String fullName;
    private String email;
    private Integer age;
    private Integer yearsOfExperience;
    private String phone;
    private String address;
    private String bio;
}