package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBabyLionRequest {
    @Size(min = 2, max = 12)
    private String name;

    @Min(1) @Max(4)
    private Integer grade;

    @Email @NotBlank
    private String email;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$")
    private String phoneNumber;

    @NotBlank
    private String introduction;
}