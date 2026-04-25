package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.request.CreateBabyLionReqDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.response.ResponseLionDto;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

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
     */
    @PostMapping()
    public ResponseEntity<String> createLion(
            @RequestBody @Valid
            CreateBabyLionReqDto createBabyLionReqDto
    ) {

        babyLionService.createLion(createBabyLionReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("아기사자 등록되었습니다.");
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
