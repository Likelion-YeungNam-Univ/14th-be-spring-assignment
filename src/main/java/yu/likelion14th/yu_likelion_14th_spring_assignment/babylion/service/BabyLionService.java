package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.LionListDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.OneLionDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.InMemoryBabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final InMemoryBabyLionRepository babyLionRepository;

    // 아기사자 등록
    public long createLion(CreateBabyLionReqDto dto){
        for (BabyLion lion : babyLionRepository.findAll()) {
            // 학번 중복체크
            if(lion.getStudentId().equals(dto.getStudentId())){
                throw new CustomException(ErrorCode.STUDENTID_DUPLICATED);
            }
            // 이메일 중복체크
            if(lion.getEmail().equals(dto.getEmail())){
                throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
            }
        }
        // 빌더를 써서 저장해주기
        BabyLion babyLion = BabyLion.builder()
                .name(dto.getName())
                .studentId(dto.getStudentId())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();

        //저장됐을때 ID가 몇번인지 나오게 함
        return babyLionRepository.save(babyLion).getId();
    }


    //아기사자 전체조회
    public List<LionListDto> getLions(Integer grade) {
        List<LionListDto> result = new ArrayList<>();
        List<BabyLion> all = babyLionRepository.findAll();
       // 학년이 없으면 result에 담기
        if (grade == null) {
            for (BabyLion lion : all) {
                result.add(new LionListDto(lion.getName(), lion.getIntroduction()));
            }
        }
        // 학년이 있을 때 그 학년만 result에 담아서 출력
        else {
            for (BabyLion lion : all) {
                if (lion.getGrade().equals(grade)) {
                    result.add(new LionListDto(lion.getName(), lion.getIntroduction()));
                }
            }
        }
        return result;
    }


    // 아기사자 개별 조회
    public OneLionDto getLionOne(Long id) {
        BabyLion lion = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
        return new OneLionDto(lion.getEmail(), lion.getPhoneNumber());
    }


    // 아기사자 수정
    public void updateLion(Long id, UpdateBabyLionReqDto dto) {
        BabyLion lion = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
        // 원래 본인의 이메일을 제외한 다른 이메일 중복 방지
        if (dto.getEmail() != null) {
            for (BabyLion other : babyLionRepository.findAll()) {
                if (!other.getId().equals(id) && other.getEmail().equals(dto.getEmail())) {
                    throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
                }
            }
        }
        //
        BabyLion update = BabyLion.builder()
                // id와 학번을 제외한 수정내용 저장
                .id(lion.getId())
                .studentId(lion.getStudentId())
                .name(dto.getName())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();

        babyLionRepository.save(update);
    }

    // 예제

    /**
     * 아기사자 삭제
     *
     * @param id 아기사자 식별자(ID)
     */
    public void deleteLion(Long id) {

        // 아기사자 엔티티 불러오기 -> 없으면 404 예외처리
        BabyLion target = babyLionRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND)
        );

        // 아기사자 정보 삭제
        babyLionRepository.delete(target.getId());
    }
}
