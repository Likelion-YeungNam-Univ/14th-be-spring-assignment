package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.BabyLionInfoResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.BabyLionListResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final InMemoryBabyLionRepository babyLionRepository;

    // 구현 시작

    // 아기사자 등록
    public BabyLion createLion(CreateBabyLionReqDto dto) {

        // 학번 중복 체크
        boolean isDuplicatedStudentId = babyLionRepository.findAll().stream()
                .anyMatch(lion -> lion.getStudentId().equals(dto.getStudentId()));
        if (isDuplicatedStudentId) {
            throw new CustomException(ErrorCode.DUPLICATED_STUDENT_ID);
        }

        // 이메일 중복 체크
        boolean isDuplicatedEmail = babyLionRepository.findAll().stream()
                .anyMatch(lion -> lion.getEmail().equals(dto.getEmail()));
        if (isDuplicatedEmail) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 빌더 써서 엔티티 생성 후 저장
        BabyLion babyLion = BabyLion.builder()
                .name(dto.getName())
                .studentId(dto.getStudentId())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();

        return babyLionRepository.save(babyLion);
    }

    // 아기사자 전체 조회, 학년 필터링
    public List<BabyLionListResDto> getLions(Integer grade) {

        List<BabyLion> lions = babyLionRepository.findAll();

        if (grade != null) {
            lions = lions.stream()
                    .filter(lion -> lion.getGrade().equals(grade))
                    .toList();
        }

        return lions.stream()
                .map(lion -> new BabyLionListResDto(lion))
                .toList();
    }

    // 아기사자 개별 조회
    public BabyLionInfoResDto getLion(Long id) {

        BabyLion babyLion = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        return new BabyLionInfoResDto(babyLion);
    }

    // 아기사자 정보 수정
    public BabyLion updateLion(Long id, UpdateBabyLionReqDto dto) {

        // id로 아기사자 찾기
        BabyLion babyLion = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        // 이메일 중복 체크
        boolean isDuplicatedEmail = babyLionRepository.findAll().stream()
                .anyMatch(lion -> lion.getEmail().equals(dto.getEmail())
                        && !lion.getId().equals(id));
        if (isDuplicatedEmail) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 수정된 엔티티 생성 후 저장
        BabyLion updated = BabyLion.builder()
                .id(babyLion.getId())
                .studentId(babyLion.getStudentId())
                .name(dto.getName())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();

        return babyLionRepository.save(updated);
    }



    // 예제

    /**
     * 아기사자 삭제
     *
     * @param id 아기사자 식별자(ID)
     */
    public void deleteLion(Long id) {

        // 아기사자 엔티티 불러오기 -> 없으면 404 예외처리
        BabyLion target = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        // 아기사자 정보 삭제
        babyLionRepository.delete(target.getId());
    }
}
