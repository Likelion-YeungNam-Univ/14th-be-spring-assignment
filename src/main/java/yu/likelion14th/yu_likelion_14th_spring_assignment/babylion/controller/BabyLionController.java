package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 구현 시작

    /**
     * 아기사자 등록
     *
     * @param createBabyLionReqDto 아기사자 정보 DTO
     * @return 성공: 201
     */
    @PostMapping
    public ResponseEntity<?> createLion (
            @RequestBody @Valid
            CreateBabyLionReqDto createBabyLionReqDto
    ) {

        babyLionService.createLion(createBabyLionReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("아기사자 등록되었습니다.");
    }

    /**
     * 아기사자 조회 및 학년별 조회
     *
     * @param grade 학년
     * @return 아기사자 정보 리스트
     */
    @GetMapping
    public ResponseEntity<List<BabyLionResDto>> findAllLions (
            @RequestParam(required = false) Integer grade
    ) {

        return ResponseEntity.ok(babyLionService.findAllLions(grade));
    }

    /**
     * 아기사자 개별 연락처 조회
     *
     * @param id 아기사자 id
     * @return 아기사자 연락처 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<BabyLionContactResDto> findLion (
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(babyLionService.findLion(id));
    }

    /**
     * 아기사자 정보 수정
     *
     * @param id 아기사자 id
     * @param updateBabyLionReqDto 아기사자 정보 수정 DTO
     * @return 200
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid
            UpdateBabyLionReqDto updateBabyLionReqDto
    ) {

        babyLionService.updateLion(id, updateBabyLionReqDto);
        return ResponseEntity.ok("아기사자 정보가 수정되었습니다.");
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
