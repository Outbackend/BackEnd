package outBackend.cloudProject.apiPayload.exception.handler;

import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.exception.GeneralException;

public class ProjectHandler extends GeneralException {

    public ProjectHandler(ErrorCode code){
        super(code);
    }
}
