package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BabyLion {

    // 아래 필드들의 조건에 따라 DTO구성, Validation, Service의 예외처리를 적절하게 해주시면 됩니다.
    // 그 외 현실상황에서 디테일하게 처리하고 싶은 부분은 더 처리해주셔도 좋습니다.

    /**
     * 식별자 (ID)
     * - 제약: 중복 방지 (Unique)
     * - 주의: Repository 저장 시 자동 생성되며, 직접 초기화 및 수정 불가
     */
    public Long id;

    /**
     * 아기사자 이름
     * - 제약: 최소 2자 ~ 최대 12자
     */
    public String name;

    /**
     * 학번 (Student ID)
     * - 제약: 중복 방지 (Unique)
     * - 주의: 생성 후 수정 불가
     */
    public String studentId;


    /**
     * 학년
     * - 제약: 1~4 사이의 값
     */
    public Integer grade;

    /**
     * 이메일
     * - 제약: 이메일 형식 (예: abc@abc.com), 중복 방지 (Unique)
     */
    public String email;

    /**
     * 전화번호
     * - 제약: 000-0000-0000 형식 준수 (정규식 활용)
     */
    public String phoneNumber;

    /**
     * 자기소개
     * - 제약: Not Blank (빈칸/공백 불가)
     */
    public String introduction;


    /* --- 비즈니스 로직 및 편의 메서드 --- */
    @Builder
    public BabyLion(String name, String studentId, Integer grade, String email, String phoneNumber, String introduction) {
        this.name = name;
        this.studentId = studentId;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }

    public void update(UpdateBabyLionReqDto UpdateBabyLionReqDto) {
        if (UpdateBabyLionReqDto.name() != null) this.name = UpdateBabyLionReqDto.name();
        if (UpdateBabyLionReqDto.grade() != null) this.grade = UpdateBabyLionReqDto.grade();
        if (UpdateBabyLionReqDto.email() != null) this.email = UpdateBabyLionReqDto.email();
        if (UpdateBabyLionReqDto.phoneNumber() != null) this.phoneNumber = UpdateBabyLionReqDto.phoneNumber();
        if (UpdateBabyLionReqDto.introduction() != null && !UpdateBabyLionReqDto.introduction().isBlank()) this.introduction = UpdateBabyLionReqDto.introduction();
    }

    /**
     * 식별자 설정을 위한 메서드
     * Repository의 시퀀스 로직에서 사용되며, 외부에서의 직접 호출은 권장하지 않습니다.
     */
    public void setId(long l) {
        this.id = l;
    }

    // 그 외 구현해보고 싶은 필드는 자유롭게 추가해서 활용


}
