package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service.BabyLionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/babylions")
public class BabyLionController {
    private final BabyLionService babyLionService;

    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody CreateBabyLionRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(babyLionService.registerLion(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BabyLionResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(babyLionService.getLion(id));
    }

    @GetMapping
    public ResponseEntity<List<BabyLionResponse>> getAll() {
        return ResponseEntity.ok(babyLionService.findAllLions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UpdateBabyLionRequest dto) {
        babyLionService.updateLion(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        babyLionService.deleteLion(id);
        return ResponseEntity.noContent().build();
    }
}