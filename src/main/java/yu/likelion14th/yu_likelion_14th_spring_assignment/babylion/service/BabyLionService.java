package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionContactResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionListResDto;

import java.util.List;

public interface BabyLionService {

    Long createLion(CreateBabyLionReqDto requestDto);

    List<BabyLionListResDto> getLions(Integer grade);

    BabyLionContactResDto getLionContact(Long id);

    void updateLion(Long id, UpdateBabyLionReqDto requestDto);

    void deleteLion(Long id);
}