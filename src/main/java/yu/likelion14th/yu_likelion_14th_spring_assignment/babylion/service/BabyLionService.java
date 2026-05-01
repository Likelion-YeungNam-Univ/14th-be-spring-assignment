package yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.dto.*;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.entity.BabyLion;
import yu.likelion14th.yu_likelion_14th_spring_assignment.babylion.repository.BabyLionRepository;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.CustomException;
import yu.likelion14th.yu_likelion_14th_spring_assignment.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BabyLionService {
    private final BabyLionRepository babyLionRepository;

    public Long registerLion(CreateBabyLionRequest dto) {
        if (babyLionRepository.findByStudentId(dto.getStudentId()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_STUDENT_ID);
        }
        if (babyLionRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        BabyLion babyLion = BabyLion.builder()
                .name(dto.getName())
                .studentId(dto.getStudentId())
                .grade(dto.getGrade())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .introduction(dto.getIntroduction())
                .build();

        return babyLionRepository.save(babyLion).getId();
    }

    public BabyLionResponse getLion(Long id) {
        BabyLion babyLion = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
        return BabyLionResponse.from(babyLion);
    }

    public List<BabyLionResponse> findAllLions() {
        return babyLionRepository.findAll().stream()
                .map(BabyLionResponse::from)
                .collect(Collectors.toList());
    }

    public void updateLion(Long id, UpdateBabyLionRequest dto) {
        BabyLion target = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));

        target.update(dto.getName(), dto.getGrade(), dto.getEmail(), dto.getPhoneNumber(), dto.getIntroduction());
        babyLionRepository.save(target);
    }

    public void deleteLion(Long id) {
        BabyLion target = babyLionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BABYLION_NOT_FOUND));
        babyLionRepository.deleteById(target.getId());
    }
}