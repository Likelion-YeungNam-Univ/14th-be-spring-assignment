package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LionListDto {
    //BabyLion 전체 조회 응답에 사용할 dto
    private String name;
    private String introduction;
}
