package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
public class BabyLionListResDTO{
    // 임시
    private String name;
    private String introduction;

    public static BabyLionListResDTO from(BabyLion babyLion) {
        return new BabyLionListResDTO(
                babyLion.getName(),
                babyLion.getIntroduction()
        );
    }