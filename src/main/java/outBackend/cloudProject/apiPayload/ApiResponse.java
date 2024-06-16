package outBackend.cloudProject.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import outBackend.cloudProject.apiPayload.code.status.SuccessStatus;


//  api 응답 형식 통일
//  RestController에서 ApiResponse 형식을 반환
//  code와 message 부분은 직접 입력하지 않는다. apiPayload\code\status 폴더의 enum class 안에 상수 값을 정의하고 가져와 사용한다.
//  result에는 controller에서 반환 시 dto를 넣어준다.
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;


    //  요청을 성공적으로 처리 후 return 할 때 ApiResponse.onSuccess() 형식으로 반환
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    //  apiPayload\exception\ExceptionAdvice 에서 Uncheck Exception 처리하기 위해 사용.
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}
