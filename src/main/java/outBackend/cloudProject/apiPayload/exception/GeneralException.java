package outBackend.cloudProject.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private ErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
}
