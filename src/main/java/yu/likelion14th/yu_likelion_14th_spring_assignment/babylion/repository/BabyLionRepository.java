package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import org.springframework.stereotype.Repository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

import java.util.List;
import java.util.Optional;

@Repository
public interface BabyLionRepository {
    BabyLion save(BabyLion babyLion);
    Optional<BabyLion> findById(Long id);
    List<BabyLion> findAll();
    public void delete(Long id);
    boolean existsByEmail(String email);
    boolean existsByStudentId(String studentId);
}
