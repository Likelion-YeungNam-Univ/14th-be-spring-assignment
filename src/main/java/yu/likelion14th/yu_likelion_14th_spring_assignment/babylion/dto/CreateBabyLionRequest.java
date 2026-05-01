package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBabyLionRequest {
    @Size(min = 2, max = 12, message = "이름은 2자에서 12자 사이여야 합니다.")
    private String name;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentId;

    @Min(1) @Max(4)
    private Integer grade;

    @Email @NotBlank
    private String email;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 형식을 확인하세요.")
    private String phoneNumber;

    @NotBlank(message = "자기소개는 필수입니다.")
    private String introduction;
}