package outBackend.cloudProject.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements ErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청입니다"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "403", "금지된 요청입니다."),

    _SKILLTAG_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "존재하지 않는 스킬태그입니다."),

    _MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "400", "존재하지 않는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }
}
