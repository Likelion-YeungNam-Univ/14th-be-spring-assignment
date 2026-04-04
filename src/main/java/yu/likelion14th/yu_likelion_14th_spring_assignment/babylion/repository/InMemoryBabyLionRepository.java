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
    public BabyLion save(BabyLion babyLion) {
        if (babyLion.getId() == null) {
            babyLion.setId(++sequence);
        }
        store.put(babyLion.getId(), babyLion);
        return babyLion;
    }

    // 식별자 기반 엔티티 조회
    public Optional<BabyLion> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 전체 조회
    public List<BabyLion> findAll() {
        return new ArrayList<>(store.values());
    }

    // 엔티티 삭제
    public void delete(Long id) {
        store.remove(id);
    }

}
