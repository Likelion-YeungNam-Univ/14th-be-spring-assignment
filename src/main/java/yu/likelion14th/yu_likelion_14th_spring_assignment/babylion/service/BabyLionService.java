package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionRequestDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionRequestDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResponseDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionListResponseDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final InMemoryBabyLionRepository babyLionRepository;

    // 구현

    /**
     * 아기사자 등록
     *
     * @param dto 등록 요청 DTO
     */
    public void createLion(CreateBabyLionRequestDto dto){

        // 학번 중복 체크
        boolean studentIdDuplicated = babyLionRepository.findAll().stream()
                .anyMatch(b -> b.getStudentId().equals(dto.getStudentId()));

        if (studentIdDuplicated) {
            throw new CustomException(ErrorCode.DUPLICATED_STUDENT_ID);
        }

        // 이메일 중복 체크
        boolean emailDuplicated = babyLionRepository.findAll().stream()
                .anyMatch(b -> b.getEmail().equals(dto.getEmail()));
        if (emailDuplicated){
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        // 엔티티 생성 후 저장
        BabyLion babyLion = BabyLion.builder()
                .name(dto.getName())
                .studentId(dto.getStudentId())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();
        babyLionRepository.save(babyLion);
    }

    /**
     * 아기사자 전체 조회 및 학년별 필터링
     *
     * @param grade 학년 (null이면 전체 조회)
     * @return 이름, 자기소개 목록
     */
    public List<BabyLionListResponseDto> getLions(Integer grade) {
        List<BabyLion> lions = babyLionRepository.findAll();

        // grade가 있으면 필터링, 없으면 전체 반환
        if (grade != null) {
            lions = lions.stream()
                    .filter(b -> b.getGrade().equals(grade))
                    .collect(Collectors.toList());
        }
        return lions.stream()
                .map(b -> new BabyLionListResponseDto(b.getName(), b.getIntroduction()))
                .collect(Collectors.toList());
    }

    /**
     * 아기사자 개별 연락처 조회
     *
     * @param id 아기사자 식별자(ID)
     * @return 이메일, 전화번호
     */
    public BabyLionContactResponseDto getLionContact(Long id) {

        // 아기사자 엔티티 불러오기 -> 없으면 404 예외처리
        BabyLion target = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        return new BabyLionContactResponseDto(target.getEmail(), target.getPhoneNumber());
    }

    /**
     * 아기사자 정보 수정
     *
     * @param id 아기사자 식별자(ID)
     * @param dto 수정 요청 DTO
     */
    public void updateLion(Long id, UpdateBabyLionRequestDto dto) {

        // 아기사자 엔티티 불러오기 -> 없으면 404 예외처리
        BabyLion target = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        // 이메일 변경 시 중복 체크 (자기 자신 제외)
        if (dto.getEmail() != null) {
            boolean emailDuplicated = babyLionRepository.findAll().stream()
                    .filter(b -> !b.getId().equals(id))
                    .anyMatch(b -> b.getEmail().equals(dto.getEmail()));

            if (emailDuplicated){
                throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
            }
        }

        // 수정 가능한 필드 없데이트
        target.update(dto.getName(), dto.getGrade(), dto.getEmail(),
                dto.getPhoneNumber(), dto.getIntroduction());
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
