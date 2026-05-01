package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 구현 시작

    // 아기사자 등록 API
    @PostMapping
    public ResponseEntity<?> createLion(
            @RequestBody @Valid CreateBabyLionReqDto dto
    ) {
        BabyLion babyLion = babyLionService.createLion(dto);
        return ResponseEntity.ok(babyLion);
    }

    // 전체조회, 학년 필터링 API
    @GetMapping
    public ResponseEntity<?> getLions(
            @RequestParam(required = false) Integer grade
    ) {
        return ResponseEntity.ok(babyLionService.getLions(grade));
    }

    // 개별 연락처 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getLion(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(babyLionService.getLion(id));
    }

    // 아기사자 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBabyLionReqDto dto
    ) {
        BabyLion babyLion = babyLionService.updateLion(id, dto);
        return ResponseEntity.ok(babyLion);
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
