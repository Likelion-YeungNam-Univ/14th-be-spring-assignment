package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateBabyLionReqDTO{
    @Size(min = 2,max = 12, message = "이름은 2자 이상 12자 이하")
    private String name;

    @Min(value = 1) @Max(value = 4)
    private Integer grade;

    @Email
    private String email;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}")
    private String phoneNumber;

    private String introduction;
}