package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BABYLION_NOT_FOUND(404, "아기사자를 찾을 수 없습니다."),
    DUPLICATE_STUDENT_ID(400, "이미 존재하는 학번입니다."),
    DUPLICATE_EMAIL(400, "이미 존재하는 이메일입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류입니다.");

    // 추가하려면 마지막에만 세미콜론 처리
    // 예외 사이는 콤마로 처리

    private final int status;
    private final String message;
}
