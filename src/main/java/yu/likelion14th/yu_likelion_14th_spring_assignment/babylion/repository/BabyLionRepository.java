package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import org.springframework.stereotype.Repository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

import java.util.List;
import java.util.Optional;

@Repository
public interface BabyLionRepository {

    // 아기사자 저장 / 수정
    BabyLion save(BabyLion babyLion);

    // 아기사자 ID로 조회
    Optional<BabyLion> findById(Long id);

    // 아기사자 전체 조회
    List<BabyLion> findAll();

    // 아기사자 삭제
    void delete(Long id);

    // 아기사자 이메일 중복 검증 메소드
    boolean existsByEmail(String email);

    // 아기사자 학번 중복 검증 메소드
    boolean existsByStudentId(String studentId);
}