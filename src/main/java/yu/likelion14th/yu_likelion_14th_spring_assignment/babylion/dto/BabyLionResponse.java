package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@AllArgsConstructor
@Builder
public class BabyLionResponse {
    private Long id;
    private String name;
    private String studentId;
    private Integer grade;
    private String email;
    private String phoneNumber;
    private String introduction;

    public static BabyLionResponse from(BabyLion entity) {
        return BabyLionResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .studentId(entity.getStudentId())
                .grade(entity.getGrade())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .introduction(entity.getIntroduction())
                .build();
    }
}