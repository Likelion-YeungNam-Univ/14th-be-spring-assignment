package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import org.springframework.stereotype.Repository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBabyLionRepository implements BabyLionRepository {

    private final Map<Long, BabyLion> store = new HashMap<>();
    private long sequence = 1L;

    public BabyLion save(BabyLion lion) {
        lion.setId(sequence++);
        store.put(lion.getId(), lion);
        return lion;
    }

    public List<BabyLion> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<BabyLion> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<BabyLion> findByGrade(Integer grade) {
        return store.values().stream()
                .filter(lion -> lion.getGrade().equals(grade))
                .toList();
    }

    public void delete(BabyLion lion) {
        store.remove(lion.getId());
    }

    public boolean existsByStudentId(String studentId) {
        return store.values().stream()
                .anyMatch(lion -> lion.getStudentId().equals(studentId));
    }

    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(lion -> lion.getEmail().equals(email));
    }
}