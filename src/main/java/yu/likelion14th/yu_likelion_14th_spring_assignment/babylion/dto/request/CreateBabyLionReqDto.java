package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateBabyLionReqDto {
    // Validation 활용하여 잘못된 데이터 안오게 함
    // BabyLion을 Post할때 사용할 dto

    @NotBlank(message = "이름은 필수이며 공백일 수 없습니다.") // 공백이면 안됨
    @Size(min = 2, max = 12, message="이름은 2~12자 사이여야 합니다.") // 글자 수를 정해주기
    private String name;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentId;

    @NotNull(message = "학년은 필수입니다.")
    @Min(value = 1, message = "학년은 1학년부터 가능합니다.") // 숫자 최소치 정해주기
    @Max(value = 4, message = "학년은 4학년까지만 존재합니다.") // 숫자 최대치 정해주기
    private Integer grade;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.") // 이메일 형식 검증
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}",
             message = "유효한 전화번호 형식이 아닙니다.") // 전화번호 형식을 정해줌 000-0000-0000
    private String phoneNumber;

    @NotBlank(message = "자기소개는 필수입니다.")
    private String introduction;

}
