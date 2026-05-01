package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.LionListDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.OneLionDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 구현 시작

    //아기사자 등록 API
    @PostMapping
    public ResponseEntity<?> createLion(@RequestBody @Valid CreateBabyLionReqDto dto) {
        Long id = babyLionService.createLion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    //아기사자 전체 조회 API
    @GetMapping
    public ResponseEntity<?> getLions(
            @RequestParam(required = false) Integer grade) {
        return ResponseEntity.ok(babyLionService.getLions(grade));
    }

    //아기사자 개별 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getLionOne(@PathVariable Long id) {
        return ResponseEntity.ok(babyLionService.getLionOne(id));
    }

    //아기사자 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBabyLionReqDto dto) {
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
    public ResponseEntity<?> deleteLion(@PathVariable Long id){
        babyLionService.deleteLion(id);
        return ResponseEntity.ok().build();
    }

}
