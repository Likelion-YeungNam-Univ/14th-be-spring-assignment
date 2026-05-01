package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto;

import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
public class BabyLionListResDto {

    private String name;
    private String introduction;

    public BabyLionListResDto(BabyLion babyLion) {
        this.name = babyLion.getName();
        this.introduction = babyLion.getIntroduction();
    }
}