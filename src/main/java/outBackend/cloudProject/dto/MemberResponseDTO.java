package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.SkillTag;

import java.util.List;
import java.util.Optional;

public class MemberResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long id;
        String nickName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO{
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPageDTO{
        Long id;
        String nickName;
        String intro;
        String about;
        List<String> skillTagList;
        List<String> projectList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateUserResultDTO{
        String nickName;
        String intro;
        String about;

        List<String> skillTagList;
    }
}
