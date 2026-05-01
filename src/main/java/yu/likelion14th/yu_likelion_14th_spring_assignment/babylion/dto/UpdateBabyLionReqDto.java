package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateBabyLionReqDto {

    @Size(min = 2, max = 12)
    private String name;

    @Min(1) @Max(4)
    private Integer grade;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호 형식을 맞춰주세요. 예시) 010-0000-0000")
    private String phoneNumber;

    @NotBlank
    private String introduction;
}