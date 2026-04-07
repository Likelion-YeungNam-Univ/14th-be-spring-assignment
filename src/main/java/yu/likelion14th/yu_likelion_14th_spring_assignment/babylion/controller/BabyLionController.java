package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions") // 내부에 공통 엔드포인트 입력
public class BabyLionController {

    private final BabyLionService babyLionService;

    // 구현 시작










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
