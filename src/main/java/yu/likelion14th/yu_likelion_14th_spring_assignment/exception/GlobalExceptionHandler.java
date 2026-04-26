package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외처리
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDto> customExceptionHandler(
            CustomException e,
            HttpServletRequest request) {

        ErrorCode errorCode = e.getErrorCode();

        ErrorDto errorDto = new ErrorDto(
                errorCode.getCode(),
                errorCode.getStatus(),
                errorCode.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorDto);
    }

    // 일반 예외처리
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> customServerException(
            Exception e,
            HttpServletRequest request){

        log.error("INTERNAL_SERVER_ERROR", e);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorDto errorDto = new ErrorDto(
                errorCode.getCode(),
                errorCode.getStatus(),
                errorCode.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorDto);
    }

    // 메소드 인자 타당성 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidationException(
        MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for(FieldError error : e.getBindingResult().getFieldErrors())
            errors.put(error.getField(), error.getDefaultMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // @RequestParam 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorDto> handleMissingParam(
            MissingServletRequestParameterException ex,
            HttpServletRequest request) {

        ErrorCode errorCode = ErrorCode.MISSING_REQUEST_PARAMETER;

        String msg = ex.getParameterName() + " 파라미터는 필수입니다.";

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorDto(
                        errorCode.getCode(),
                        errorCode.getStatus(),
                        msg,
                        LocalDateTime.now(),
                        request.getRequestURI()
                ));
    }

    // 타입/형식 변환 실패
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorDto> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        Throwable cause = ex.getCause();

        if (cause instanceof java.time.format.DateTimeParseException) {

            ErrorCode errorCode = ErrorCode.INVALID_DATE_FORMAT;

            return ResponseEntity
                    .status(errorCode.getStatus())
                    .body(new ErrorDto(
                            errorCode.getCode(),
                            errorCode.getStatus(),
                            errorCode.getMessage(),
                            LocalDateTime.now(),
                            request.getRequestURI()
                    ));
        }

        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER_TYPE;

        String msg = ex.getName() + " 파라미터 형식이 올바르지 않습니다.";

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorDto(
                        errorCode.getCode(),
                        errorCode.getStatus(),
                        msg,
                        LocalDateTime.now(),
                        request.getRequestURI()
                ));
    }

}
