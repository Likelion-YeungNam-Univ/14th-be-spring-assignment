package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통 (Common) - Cxxx
    INVALID_INPUT_VALUE("C001", 400, "잘못된 입력값입니다."),
    METHOD_NOT_ALLOWED("C002", 405, "허용되지 않은 HTTP 메서드입니다."),
    ENTITY_NOT_FOUND("C003", 404, "대상을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR("C004", 500, "내부 서버 오류입니다."),
    INVALID_TYPE_VALUE("C005", 400, "잘못된 타입입니다."),
    MISSING_REQUEST_PARAMETER("C006", 400, "필수 파라미터가 누락되었습니다."),
    REQUEST_BODY_MISSING("C007", 400, "요청 바디가 존재하지 않습니다."),
    JSON_PARSE_ERROR("C008", 400, "JSON 파싱 오류입니다."),
    INVALID_DATE_FORMAT("C009", 400, "날짜 형식이 올바르지 않습니다. (yyyy-MM-dd)"),
    INVALID_PARAMETER_TYPE("C010", 400, "파라미터 형식이 올바르지 않습니다."),

    // 아기사자 (BabyLion) - Bxxx
    BABYLION_NOT_FOUND("B001", 404, "아기사자를 찾을 수 없습니다."),
    BABYLION_ALREADY_EXISTS("B002", 409, "이미 존재하는 아기사자입니다."),
    BABYLION_STUDENTID_DUPLICATED("B003", 409, "이미 존재하는 학번입니다."),
    BABYLION_EMAIL_DUPLICATED("B004", 409, "이미 존재하는 이메일입니다."),
    BABYLION_NAME_DUPLICATED("B005", 409, "중복된 이름입니다."),
    BABYLION_INVALID_AGE("B006", 400, "유효하지 않은 나이입니다."),
    BABYLION_INVALID_STATUS("B007", 400, "유효하지 않은 상태값입니다."),
    BABYLION_DELETE_FAILED("B008", 500, "아기사자 삭제에 실패했습니다."),

    // 서버 상태 - Sxxx
    SERVICE_UNAVAILABLE("S001", 503, "서비스를 사용할 수 없습니다."),
    TIMEOUT("S002", 504, "요청 시간이 초과되었습니다.");

    private final String code;
    private final int status;
    private final String message;
}
