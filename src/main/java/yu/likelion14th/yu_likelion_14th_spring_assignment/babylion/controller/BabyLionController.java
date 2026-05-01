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
@RequestMapping("/api/v1/babylions")
public class BabyLionController {

    private final BabyLionService babyLionService;

     //아기사자 등록 API
    @PostMapping
    public ResponseEntity<?> createLion(
            @RequestBody @Valid CreateBabyLionReqDto dto
    ) {
        babyLionService.createLion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("아기사자가 등록되었습니다.");
    }


    //아기사자 전체 조회 API
    @GetMapping
    public ResponseEntity<List<BabyLionResDto>> findAllLions(
            @RequestParam(required = false) Integer grade
    ) {
        return ResponseEntity.ok(babyLionService.findAllLions(grade));
    }

    //아기사자 개별 연락처 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<BabyLionContactResDto> findLion(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(babyLionService.findLion(id));
    }

    // ─────────────────────────────────────────────
    // 4. 아기사자 정보 수정
    // ─────────────────────────────────────────────

    //아기사자 정보 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBabyLionReqDto dto
    ) {
        babyLionService.updateLion(id, dto);
        return ResponseEntity.ok("아기사자 정보가 수정되었습니다.");
    }


 //아기사자 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLion(
            @PathVariable Long id
    ) {
        babyLionService.deleteLion(id);
        return ResponseEntity.ok().build();
    }
}