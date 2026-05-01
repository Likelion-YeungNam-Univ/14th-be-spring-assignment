package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

 // 아기사자 전체 조회 응답 DTO
public record BabyLionResDto(
        String name,
        String introduction //이름, 자기소개만 반환
) {
    //엔티티 ->  DTO 반환 메서드
    public static BabyLionResDto from(BabyLion babyLion) {
        return new BabyLionResDto(
                babyLion.getName(),
                babyLion.getIntroduction()
        );
    }
}