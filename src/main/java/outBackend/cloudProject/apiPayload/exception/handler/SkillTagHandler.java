package outBackend.cloudProject.apiPayload.exception.handler;

import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.exception.GeneralException;

public class SkillTagHandler extends GeneralException {

    public SkillTagHandler(ErrorCode code) {
        super(code);
    }
}
