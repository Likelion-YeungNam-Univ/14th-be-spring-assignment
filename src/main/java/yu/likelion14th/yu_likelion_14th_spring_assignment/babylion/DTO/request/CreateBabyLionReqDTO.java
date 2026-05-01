package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@NoArgsConstructor
public class CreateBabyLionReqDTO {
    @NotBlank
    @Size(min = 2,max = 12, message = "이름은 2자 이상 12자 이하")
    private String name;

    @NotBlank // 중복 처리는 추후에 진행
    private String studentId;

    @NotNull
    @Min(value = 1) @Max(value = 4)
    private Integer grade;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}")
    private String phoneNumber;

    @NotBlank
    private String introduction;

    public BabyLion toEntity() {
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