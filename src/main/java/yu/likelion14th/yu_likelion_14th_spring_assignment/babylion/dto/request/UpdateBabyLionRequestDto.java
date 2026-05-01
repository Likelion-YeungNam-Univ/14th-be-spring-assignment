package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBabyLionRequestDto {

    @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하입니다.")
    private String name;

    @Min(value = 1, message = "학년은 1 이상이어야 합니다.")
    @Max(value = 4, message = "학년은 4 이하여야 합니다.")
    private Integer grade;

    @Email (message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp="\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식은 000-0000-0000 입니다.")
    private String phoneNumber;

    private String introduction;
}
