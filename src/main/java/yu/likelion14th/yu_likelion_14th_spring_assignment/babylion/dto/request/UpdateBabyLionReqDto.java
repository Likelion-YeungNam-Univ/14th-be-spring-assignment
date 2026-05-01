package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateBabyLionReqDto {
    // BabyLion을 PATCH할때 사용할 dto
    // 학번은 수정하면 안되기 때문에 제외

    @Size(min = 2, max = 12, message="이름은 2~12자 사이여야 합니다.")
    private String name;

    @Min(value = 1, message = "학년은 1학년부터 가능합니다.")
    @Max(value = 4, message = "학년은 4학년까지만 존재합니다.")
    private Integer grade;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}")
    private String phoneNumber;

    private String introduction;


}
