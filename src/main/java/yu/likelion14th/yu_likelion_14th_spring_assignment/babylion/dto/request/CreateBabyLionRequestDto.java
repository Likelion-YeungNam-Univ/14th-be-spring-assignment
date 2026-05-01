package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBabyLionRequestDto {

    @NotBlank (message = "이름은 필수입니다.")
    @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하입니다.")
    private String name;

    @NotBlank (message = "학번은 필수입니다.")
    private String studentId;

    @NotNull (message = "학년은 필수입니다.")
    @Min(value = 1, message = "학년은 1 이상이어야 합니다.")
    @Max(value = 4, message = "학년은 4 이하여야 합니다.")
    private Integer grade;

    @NotBlank (message = "이메일은 필수입니다.")
    @Email (message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank (message = "전화번호는 필수입니다.")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식은 000-0000-0000입니다.")
    private String phoneNumber;

    @NotBlank (message = "자기소개는 필수입니다.")
    private String introduction;
}
