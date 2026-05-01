package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

//아기사자 등록 요청 DTO
public record CreateBabyLionReqDto(

        @NotBlank(message = "이름은 필수로 입력해주세요.")
        @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하로 입력해주세요.")
        String name,

        @NotBlank(message = "학번은 필수로 입력해주세요.")
        String studentId,

        @NotNull(message = "학년은 필수로 입력해주세요.")
        @Min(value = 1, message = "1학년부터 4학년까지만 가능합니다.")
        @Max(value = 4, message = "1학년부터 4학년까지만 가능합니다.")
        Integer grade,

        @NotBlank(message = "이메일은 필수로 입력해주세요.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email,

        @NotBlank(message = "전화번호는 필수로 입력해주세요.")
        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식은 000-0000-0000 입니다.")
        String phoneNumber,

        @NotBlank(message = "자기소개는 필수로 입력해주세요.")
        String introduction

) {
     //엔티티 ->  DTO 반환 메서드
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