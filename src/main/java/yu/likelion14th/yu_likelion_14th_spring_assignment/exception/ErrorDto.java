package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDto {

    private final String code;
    private final int status;
    private final String message;
    private LocalDateTime timestamp;
    private String path;
}
