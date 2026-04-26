package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionContactResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.reseponse.BabyLionListResDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/babylions")
@RequiredArgsConstructor
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 1. 등록
    @PostMapping
    public ResponseEntity<?> postLion(
            @RequestBody @Valid CreateBabyLionReqDto requestDto
    ){
        Long id = babyLionService.createLion(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // 2. 전체 조회 + 학년 필터링
    @GetMapping
    public ResponseEntity<?> getLions(
            @RequestParam(required = false) Integer grade
    ){
        List<BabyLionListResDto> result = babyLionService.getLions(grade);
        return ResponseEntity.ok(result);
    }

    // 3. 개별 연락처 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getLionContact(
            @PathVariable Long id
    ){
        BabyLionContactResDto result = babyLionService.getLionContact(id);
        return ResponseEntity.ok(result);
    }

    // 4. 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBabyLionReqDto requestDto
    ){
        babyLionService.updateLion(id, requestDto);
        return ResponseEntity.ok().build();
    }

    // 5. 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLion(
            @PathVariable Long id
    ){
        babyLionService.deleteLion(id);
        return ResponseEntity.ok().build();
    }
}
