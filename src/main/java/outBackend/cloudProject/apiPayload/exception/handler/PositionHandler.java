package outBackend.cloudProject.apiPayload.exception.handler;

import outBackend.cloudProject.apiPayload.code.ErrorCode;
import outBackend.cloudProject.apiPayload.exception.GeneralException;

public class PositionHandler extends GeneralException {

    public PositionHandler(ErrorCode code) {
        super(code);
    }
}
