package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import lombok.Getter;
import lombok.AllArgsConstructor;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@AllArgsConstructor
public class BabyLionListResDTO {
    private String name;
    private String introduction;

    public static BabyLionListResDTO from(BabyLion babyLion) {
        return new BabyLionListResDTO(
                babyLion.getName(),
                babyLion.getIntroduction()
        );
    }
}