package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
public class BabyLionContactResDTO{
    private String email;
    private String phoneNumber;

    public static BabyLionContactResDto from(BabyLion babyLion) {
        return new BabyLionContactResDto(
                babyLion.getEmail(),
                babyLion.getPhoneNumber()
        );
    }
}