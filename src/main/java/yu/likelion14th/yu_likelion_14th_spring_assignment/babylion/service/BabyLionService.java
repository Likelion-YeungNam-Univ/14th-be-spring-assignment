package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.BabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final BabyLionRepository babyLionRepository;

    // 구현 시작

    /**
     * 아기사자 등록
     *
     * @param createBabyLionReqDto 아기사자 정보 DTO
     */
    public void createLion(CreateBabyLionReqDto createBabyLionReqDto) {
        // 이메일 중복 검사
        if(babyLionRepository.existsByEmail(createBabyLionReqDto.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
        // 학번 중복 검사
        if(babyLionRepository.existsByEmail(createBabyLionReqDto.studentId())) {
            throw new CustomException(ErrorCode.DUPLICATE_STUDENT_ID);
        }

        BabyLion babyLion = createBabyLionReqDto.dtoToEntity();
        babyLionRepository.save(babyLion);
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
