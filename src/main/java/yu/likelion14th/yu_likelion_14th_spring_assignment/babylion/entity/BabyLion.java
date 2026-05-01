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

    /**
     * 아기사자 정보 수정 메서드
     * PPT 설계: Entity가 자신의 상태 변경 책임을 가짐
     * - id, studentId는 수정 불가 → 포함하지 않음
     * - null이 들어오면 기존 값 유지 (부분 수정 지원)
     */
    public void update(UpdateBabyLionReqDto dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.grade() != null) this.grade = dto.grade();
        if (dto.email() != null) this.email = dto.email();
        if (dto.phoneNumber() != null) this.phoneNumber = dto.phoneNumber();
        if (dto.introduction() != null && !dto.introduction().isBlank()) this.introduction = dto.introduction();
    }

    /**
     * 식별자 설정을 위한 메서드
     * Repository의 시퀀스 로직에서 사용되며, 외부에서의 직접 호출은 권장하지 않습니다.
     */
    public void setId(long l) {
        this.id = l;
    }
}