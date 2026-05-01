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
    private Long id;
    private String name;
    private String studentId; // 수정 불가
    private Integer grade;
    private String email;
    private String phoneNumber;
    private String introduction;

    public void setId(long id) {
        this.id = id;
    }

    // 수정 가능한 필드만 업데이트
    public void update(String name, Integer grade, String email, String phoneNumber, String introduction) {
        this.name = name;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }
}