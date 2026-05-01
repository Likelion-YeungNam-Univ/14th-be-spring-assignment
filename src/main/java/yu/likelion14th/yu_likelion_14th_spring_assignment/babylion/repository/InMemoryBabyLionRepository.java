package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository;

import org.springframework.stereotype.Repository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBabyLionRepository implements BabyLionRepository {
    private static Map<Long, BabyLion> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0L);

    @Override
    public BabyLion save(BabyLion babyLion) {
        if (babyLion.getId() == null) {
            babyLion.setId(sequence.incrementAndGet());
        }
        store.put(babyLion.getId(), babyLion);
        return babyLion;
    }

    @Override
    public Optional<BabyLion> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<BabyLion> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<BabyLion> findByStudentId(String studentId) {
        return store.values().stream().filter(b -> b.getStudentId().equals(studentId)).findAny();
    }

    @Override
    public Optional<BabyLion> findByEmail(String email) {
        return store.values().stream().filter(b -> b.getEmail().equals(email)).findAny();
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}