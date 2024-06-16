package outBackend.cloudProject.dto;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        String nickName;
        String email;
        String password;
        List<String> skillTagList;
    }

    @Getter
    public static class LoginDTO{
        String email;
        String password;
    }

    @Getter
    public static class updateUserDTO{

        Optional<String> nickName;
        Optional<String> intro;
        Optional<String> about;

        List<String> skillTagList;
    }
}
