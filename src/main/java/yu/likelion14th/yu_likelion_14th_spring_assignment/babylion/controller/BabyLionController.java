package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionRequestDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionRequestDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResponseDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionListResponseDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 구현 시작

    /**
     * 1. 아기사자 등록 API
     *
     * @param dto 등록 요청 DTO
     * @return 성공 여부 201
     */
    @PostMapping
    public ResponseEntity<?> createLion(
            @RequestBody @Valid CreateBabyLionRequestDto dto
    ) {
        babyLionService.createLion(dto);
        return ResponseEntity.status(201).build();
    }

    /**
     * 2. 아기사자 전체 조회 및 학년별 필터링 API
     *
     * @param grade 학년 (없으면 전체 조회)
     * @return 이름, 자기소개 목록 200
     */
    @GetMapping
    public ResponseEntity<List<BabyLionListResponseDto>> getLions(
            @RequestParam(required = false) Integer grade
    ) {
        return ResponseEntity.ok(babyLionService.getLions(grade));
    }

    /**
     * 3. 아기사자 개별 연락처 조회 API
     *
     * @param id 아기사자 식별자(ID)
     * @return 이메일, 전화번호 200/204
     */
    @GetMapping("/{id}")
    public ResponseEntity<BabyLionContactResponseDto> getLionContact(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(babyLionService.getLionContact(id));
    }

    /**
     * 4. 아기사자 정보 수정 API
     *
     * @param id 아기사자 식별자(ID)
     *        dto 수정 요청 DTO
     * @return 성공 여부 200/204
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBabyLionRequestDto dto
    ) {
        babyLionService.updateLion(id, dto);
        return ResponseEntity.ok().build();
    }

    // 예제

    /**
     * 아기사자 삭제 API
     *
     * @param id 아기사자 식별자(ID)
     * @return 성공 여부 200/404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLion(
            @PathVariable Long id
    ){
        babyLionService.deleteLion(id);
        return ResponseEntity.ok().build();
    }

}
