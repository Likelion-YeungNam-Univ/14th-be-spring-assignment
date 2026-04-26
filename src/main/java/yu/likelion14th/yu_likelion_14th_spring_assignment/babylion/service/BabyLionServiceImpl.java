package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionContactResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionListResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BabyLionServiceImpl implements BabyLionService {

    private final InMemoryBabyLionRepository inMemoryBabyLionRepository;

    // 1. 등록
    @Override
    public Long createLion(CreateBabyLionReqDto requestDto) {

        // 중복 검사
        if (inMemoryBabyLionRepository.existsByStudentId(requestDto.getStudentId())) {
            throw new CustomException(ErrorCode.BABYLION_STUDENTID_DUPLICATED);
        }

        if (inMemoryBabyLionRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.BABYLION_EMAIL_DUPLICATED);
        }

        BabyLion lion = BabyLion.builder()
                .name(requestDto.getName())
                .studentId(requestDto.getStudentId())
                .grade(requestDto.getGrade())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .introduction(requestDto.getIntroduction())
                .build();

        return inMemoryBabyLionRepository.save(lion).getId();
    }

    // 2. 전체 조회 , 학년 필터링
    @Override
    public List<BabyLionListResDto> getLions(Integer grade) {

        List<BabyLion> lions;

        if (grade != null) {
            lions = inMemoryBabyLionRepository.findByGrade(grade);
        } else {
            lions = inMemoryBabyLionRepository.findAll();
        }

        return lions.stream()
                .map(BabyLionListResDto::from)
                .toList();
    }

    // 3. 개별 연락처 조회
    @Override
    public BabyLionContactResDto getLionContact(Long id) {

        BabyLion lion = inMemoryBabyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        return BabyLionContactResDto.from(lion);
    }

    // 4. 수정
    @Override
    public void updateLion(Long id, UpdateBabyLionReqDto requestDto) {

        BabyLion lion = inMemoryBabyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        // PATCH 방식
        if (requestDto.getIntroduction() != null) {
            lion.introduction = requestDto.getIntroduction();
        }

        if (requestDto.getGrade() != null) {
            lion.grade = requestDto.getGrade();
        }

        if (requestDto.getPhoneNumber() != null) {
            lion.phoneNumber = requestDto.getPhoneNumber();
        }

        inMemoryBabyLionRepository.save(lion);
    }

    // 5. 삭제
    @Override
    public void deleteLion(Long id) {

        BabyLion lion = inMemoryBabyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        inMemoryBabyLionRepository.delete(lion);
    }
}
