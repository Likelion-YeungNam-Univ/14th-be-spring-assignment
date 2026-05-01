package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.BabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final BabyLionRepository babyLionRepository;



     //아기사자 등록, 학번 이메일 중복 검증 후 저장
    public void createLion(CreateBabyLionReqDto dto) {

        // 학번 중복 검증
        if (babyLionRepository.existsByStudentId(dto.studentId())) {
            throw new CustomException(ErrorCode.DUPLICATE_STUDENT_ID);
        }

        // 이메일 중복 검증
        if (babyLionRepository.existsByEmail(dto.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        // DTO → Entity 변환 후 저장
        BabyLion babyLion = dto.dtoToEntity();
        babyLionRepository.save(babyLion);
    }


    //아기사자 전제 조회 , 학년 필터링
    public List<BabyLionResDto> findAllLions(Integer grade) {
        return babyLionRepository.findAll().stream()
                .filter(lion -> grade == null || lion.getGrade().equals(grade))
                .map(BabyLionResDto::from)
                .toList(); // 이름 자기소개만 응답하고 grade가 null이면 전체 반환,
    }


    //아기사자 개별 연락처 조회
    public BabyLionContactResDto findLion(Long id) {
        BabyLion babyLion = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
        return BabyLionContactResDto.from(babyLion);
    }



    //아기사자 정보 수정
    public void updateLion(Long id, UpdateBabyLionReqDto dto) {

        BabyLion babyLion = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        // 이메일 변경 시 중복 검증 (본인 이메일은 허용)
        String newEmail = dto.email();
        if (newEmail != null
                && !newEmail.equals(babyLion.getEmail())
                && babyLionRepository.existsByEmail(newEmail)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        // Entity의 update() 메서드로 수정 후 저장
        babyLion.update(dto);
        babyLionRepository.save(babyLion);
    }

    //아기사자 삭제
    public void deleteLion(Long id) {

        BabyLion target = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        babyLionRepository.delete(target.getId());
    }
}