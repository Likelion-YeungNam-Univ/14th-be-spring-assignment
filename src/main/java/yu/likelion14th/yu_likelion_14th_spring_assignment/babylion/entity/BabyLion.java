package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BabyLion {

    // 아래 필드들의 조건에 따라 DTO구성, Validation, Service의 예외처리를 적절하게 해주시면 됩니다.
    // 그 외 현실상황에서 디테일하게 처리하고 싶은 부분은 더 처리해주셔도 좋습니다.

    // 식별자
    // 반드시 Repository에서 생성 시 정해져야하며, 직접 id를 초기화하면 안됨
    // 수정 불가능 주의
    public Long id;

    // 아기사자 이름
    // 최소 2자에서 최대 12자
    public String name;

    // 학번
    // 수정 불가능 주의
    public String studentId;

    // 이메일
    // 이메일 형식이어야 함 abc@abc.com와 같은 이메일 형태
    public String email;

    // 전화번호
    // 000-0000-0000 의 형식을 따라야함 (정규식을 찾아 공부해서 구현)
    public String phoneNumber;

    // 자기소개
    // 빈칸일 수 없음
    public String introduction;


    // 식별자 중복 방지 로직
    public void setId(long l) {
        this.id = l;
    }

    // 그 외 구현해보고 싶은 필드는 자유롭게 추가해서 활용

}
