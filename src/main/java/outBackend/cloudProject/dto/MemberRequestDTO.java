package outBackend.cloudProject.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        String nickName;
        String email;
        String passWord;
        List<String> skillTagList;
    }

    @Getter
    public static class LoginDTO{
        String email;
        String passWord;
    }
}
