package outBackend.cloudProject.apiPayload.exception.handler;

import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(ErrorCode code) {
        super(code);
    }
}
