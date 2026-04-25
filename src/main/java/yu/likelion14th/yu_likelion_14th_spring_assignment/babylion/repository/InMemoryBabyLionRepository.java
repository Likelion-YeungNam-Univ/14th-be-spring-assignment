package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import org.springframework.stereotype.Repository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBabyLionRepository implements BabyLionRepository{

    // 인 메모리 저장
    private static Map<Long, BabyLion> store = new HashMap<>();
    // 식별자(ID) 생성을 위한 시퀀스
    private static long sequence = 0L;

    // 엔티티 저장, 수정
    @Override
    public BabyLion save(BabyLion babyLion) {
        if (babyLion.getId() == null) {
            babyLion.setId(++sequence);
        }
        store.put(babyLion.getId(), babyLion);
        return babyLion;
    }

    // 식별자 기반 엔티티 조회
    @Override
    public Optional<BabyLion> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 전체 조회
    @Override
    public List<BabyLion> findAll() {
        return new ArrayList<>(store.values());
    }

    // 엔티티 삭제
    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    // 이메일 중복 검증
    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(lion -> lion.getEmail().equals(email));
    }

    // 학번 중복 검증
    @Override
    public boolean existsByStudentId(String studentId) {
        return store.values().stream()
                .anyMatch(lion -> lion.getStudentId().equals(studentId));
    }

}
