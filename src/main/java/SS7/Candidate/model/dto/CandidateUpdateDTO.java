package SS7.Candidate.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateUpdateDTO {

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Size(max = 200, message = "Tiểu sử không được vượt quá 200 ký tự")
    private String bio;
}