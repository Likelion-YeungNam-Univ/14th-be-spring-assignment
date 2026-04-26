package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@Builder
public class BabyLionContactResDto {

    private String email;
    private String phoneNumber;

    public static BabyLionContactResDto from(BabyLion lion) {
        return BabyLionContactResDto.builder()
                .email(lion.getEmail())
                .phoneNumber(lion.getPhoneNumber())
                .build();
    }
}