package yu.likelion14th.yu_likelion_14th_spring_assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private final int status;
    private final String message;
}
