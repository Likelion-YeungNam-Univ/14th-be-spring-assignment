package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionListResDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final InMemoryBabyLionRepository babyLionRepository;

    /**
     *아기사자 등록
     */
    public void createBabyLion(CreateBabyLionReqDTO reqDTO){
        uniEmail(reqDTO.getEmail());
        uniStudentId(reqDTO.getStudentId());
        BabyLion babyLion = reqDTO.toEntity();
        babyLionRepository.save(babyLion);
    }

    /**
     *아기사자 조회(전체 + 학년별 필터 조회
     */
    public List<BabyLionListResDTO> getBabyLions(Integer grade){
        return babyLionRepository.findAll()
                .stream()
                .filter(babyLion -> grade == null || babyLion.getGrade().equals(grade))
                .map(BabyLionListResDTO::from)
                .toList();
    }

    /**
     *아기사자 개별 연락처 조회
     */
    public BabyLionContactResDTO getBabyLionContact(Long id){
        BabyLion babyLion = findBabyLionById(id);
        return BabyLionContactResDTO.from(babyLion);
    }
    /**
     *아기사자 일부 정보 수정
     */
    public void updateBabyLion(Long id, UpdateBabyLionReqDTO requestDto) {
        BabyLion target = findBabyLionById(id);
        uniEmail(requestDto.getEmail());
        BabyLion updateBabyLion = BabyLion.builder()
                .id(target.getId())
                .studentId(target.getStudentId())
                .name(requestDto.getName() != null ? requestDto.getName() : target.getName())
                .grade(requestDto.getGrade() != null ? requestDto.getGrade() : target.getGrade())
                .email(requestDto.getEmail() != null ? requestDto.getEmail() : target.getEmail())
                .phoneNumber(requestDto.getPhoneNumber() != null ? requestDto.getPhoneNumber() : target.getPhoneNumber())
                .introduction(requestDto.getIntroduction() != null ? requestDto.getIntroduction() : target.getIntroduction())
                .build();

        babyLionRepository.save(updateBabyLion);
    }

    /**
     *아기사자 엔티티 불러오기 (일관 처리)
     */
    private BabyLion findBabyLionById(Long id){
        return babyLionRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
    }

    /**
     *아기사자 삭제
     *@param id 아기사자 식별자(ID)
     */
    public void deleteLion(Long id) {

        // 아기사자 엔티티 불러오기 -> 없으면 404 예외처리
        BabyLion target = babyLionRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        // 아기사자 정보 삭제
        babyLionRepository.delete(target.getId());
    }
    /**
     * 학번 중복 검사
     */
    private void uniStudentId(String studentId){
        boolean isDuplicate = false;
        for (BabyLion babyLion : babyLionRepository.findAll()){
            if (babyLion.getStudentId().equals(studentId)){
                isDuplicate = true;
                break;
            }
        }
        if (isDuplicate) {
            throw new CustomException(ErrorCode.STUDENT_ID_DUPLICATE);
        }
    }

    /**
     * 이메일 중복 검사
     */
    private void uniEmail(String email){
        boolean isDuplicate = babyLionRepository.findAll()
                .stream()
                .anyMatch(babyLion -> babyLion.getEmail().equals(email));
        if (isDuplicate){
            throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
        }
    }
}
