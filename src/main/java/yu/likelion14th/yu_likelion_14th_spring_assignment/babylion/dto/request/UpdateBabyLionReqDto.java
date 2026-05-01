package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;

 //아기사자 수정 요청 DTO
public record UpdateBabyLionReqDto(

        @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하입니다.")
        String name,

        @Min(value = 1, message = "1학년부터 4학년까지만 가능합니다.")
        @Max(value = 4, message = "1학년부터 4학년까지만 가능합니다.")
        Integer grade,

        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email,

        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식은 000-0000-0000 입니다.")
        String phoneNumber,

        String introduction

) {
}