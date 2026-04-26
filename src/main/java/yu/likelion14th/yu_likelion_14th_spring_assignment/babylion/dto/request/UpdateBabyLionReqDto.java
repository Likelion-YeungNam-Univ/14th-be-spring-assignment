package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateBabyLionReqDto {

    @Size(max = 500, message = "자기소개는 500자 이내입니다.")
    private String introduction;

    @Min(value = 1, message = "학년은 1 이상이어야 합니다.")
    @Max(value = 4, message = "학년은 4 이하여야 합니다.")
    private Integer grade;

    @Pattern(
            regexp = "^01[0-9]-\\d{3,4}-\\d{4}$",
            message = "전화번호 형식이 올바르지 않습니다."
    )
    private String phoneNumber;
}