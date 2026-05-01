package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import lombok.Getter;
import lombok.AllArgsConstructor;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@AllArgsConstructor
public class BabyLionContactResDTO{
    private String email;
    private String phoneNumber;

    public static BabyLionContactResDTO from(BabyLion babyLion) {
        return new BabyLionContactResDTO(
                babyLion.getEmail(),
                babyLion.getPhoneNumber()
        );
    }
}