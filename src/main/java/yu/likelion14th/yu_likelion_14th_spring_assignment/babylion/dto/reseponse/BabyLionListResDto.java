package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

@Getter
@Builder
public class BabyLionListResDto {

    private String name;
    private String introduction;

    public static BabyLionListResDto from(BabyLion lion) {
        return BabyLionListResDto.builder()
                .name(lion.getName())
                .introduction(lion.getIntroduction())
                .build();
    }
}
