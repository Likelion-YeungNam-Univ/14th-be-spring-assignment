package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.UpdateBabyLionReqDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionContactResDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.BabyLionListResDTO;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    /**
     * 아기사자 등록 API
     */
    @PostMapping
    public ResponseEntity<Void> createLion(
            @Valid @RequestBody CreateBabyLionReqDTO reqDTO){
        babyLionService.createBabyLion(reqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 아기사자 전체 조회 API
     */
    @GetMapping
    public ResponseEntity<List<BabyLionListResDTO>> getBabyLions(
        @RequestParam(required = false) Integer grade){ /// 파라미터가 없으면 전체로 반환
        List<BabyLionListResDTO> response = babyLionService.getBabyLions(grade);
        return ResponseEntity.ok(response);
    }

    /**
     * 아기사자 연락처 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<BabyLionContactResDTO> getBabyLionContact(
            @PathVariable Long id){
        BabyLionContactResDto response = babyLionService.getBabyLionContact(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 아기사자 수정 API
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBabyLion(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBabyLionReqDto requestDto
    ) {
        babyLionService.updateBabyLion(id, requestDto);
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
