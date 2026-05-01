package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response;

import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

//아기사자 개별 연락처 조회 응답 DTO
public record BabyLionContactResDto(
        String email,
        String phoneNumber
) {
    //엔티티 ->  DTO 반환 메서드
    public static BabyLionContactResDto from(BabyLion babyLion) {
        return new BabyLionContactResDto(
                babyLion.getEmail(),
                babyLion.getPhoneNumber()
        );
    }
}