package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
public class BabyLionInfoResDto {

    private String email;
    private String phoneNumber;

    public BabyLionInfoResDto(BabyLion babyLion) {
        this.email = babyLion.getEmail();
        this.phoneNumber = babyLion.getPhoneNumber();
    }
}