package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import java.util.List;
import java.util.Optional;

public interface BabyLionRepository {
    BabyLion save(BabyLion babyLion);
    Optional<BabyLion> findById(Long id);
    List<BabyLion> findAll();
    Optional<BabyLion> findByStudentId(String studentId);
    Optional<BabyLion> findByEmail(String email);
    void deleteById(Long id);
}