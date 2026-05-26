package SS7.Candidate.model.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CandidateCreateDTO {

    @NotBlank(message = "Họ và tên không được để trống")
    @Size(min = 5, max = 50, message = "Họ và tên phải từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Ứng viên phải từ 18 tuổi trở lên")
    private Integer age;

    @NotNull(message = "Năm kinh nghiệm không được để trống")
    @Min(value = 0, message = "Năm kinh nghiệm không được là số âm")
    private Integer yearsOfExperience;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Số điện thoại không hợp lệ (phải bắt đầu bằng 03,05,07,08,09 và có đúng 10 số)"
    )
    private String phone;
}
