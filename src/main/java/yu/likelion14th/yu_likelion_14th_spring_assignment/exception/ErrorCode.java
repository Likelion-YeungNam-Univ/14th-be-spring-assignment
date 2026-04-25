package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 아기사자 관련
    BABYLION_NOT_FOUND(404,"아기사자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(409, "이미 사용 중인 이메일입니다."),
    DUPLICATE_STUDENT_ID(409, "이미 사용 중인 학번입니다."),

    // 일반 오류
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류입니다.");

    // 추가하려면 마지막에만 세미콜론 처리
    // 예외 사이는 콤마로 처리

    private final int status;
    private final String message;
}
