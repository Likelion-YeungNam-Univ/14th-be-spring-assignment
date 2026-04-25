package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

public record CreateBabyLionReqDto(
        @NotBlank(message = "이름은 필수입니다")
        @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하입니다")
        String name,

        @NotBlank(message = "학번은 필수입니다")
        String studentId,

        @NotNull(message = "학년은 필수입니다")
        @Min(value = 1, message = "학년은 1 이상이어야 합니다")
        @Max(value = 4, message = "학년은 4 이하여야 합니다")
        Integer grade,

        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "이메일 형식이 올바르지 않습니다")
        String email,

        @NotBlank(message = "전화번호는 필수입니다")
        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식은 000-0000-0000 입니다")
        String phoneNumber,

        @NotBlank(message = "자기소개는 필수입니다")
        String introduction
)
{
        public BabyLion dtoToEntity() {
                return BabyLion.builder()
                        .name(name)
                        .studentId(studentId)
                        .grade(grade)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .introduction(introduction)
                        .build();
        }
}
