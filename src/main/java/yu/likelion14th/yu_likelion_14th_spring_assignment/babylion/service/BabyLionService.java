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

@Service
@RequiredArgsConstructor
public class BabyLionService {

    private final InMemoryBabyLionRepository babyLionRepository;

    /**
     *아기사자 등록
     */
    public void createBabyLion(CreatebabyLionReqDTO reqDTO){

    }

    /**
     *아기사자 조회(전체 + 학년별 필터 조회
     */
    public List<BabyLionResDTO> getBabyLions(Integer grade){

    }

    /**
     *아기사자 개별 연락처 조회
     */
    public BabyLionContactResDTO getBabyLionContact(Long id){

    }
    /**
     *아기사자 일부 정보 수정
     */
    public void updateBabyLion(Long id, UpdateBabyLionReqDto requestDto) {

    }

    /**
     *아기사자 엔티티 불러오기 (일관 처리)
     */
    private BabyLion findBabyLionById(Long id){
        return babyLionRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND))
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
}
