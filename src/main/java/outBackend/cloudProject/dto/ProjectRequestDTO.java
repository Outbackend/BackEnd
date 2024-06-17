package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProjectRequestDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class SaveDTO{
        String title;
        String content;
        LocalDate deadline;
        List<String> skillTagList;
        List<String> positionList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class addMemberToProjectDTO{
        Long projectId;
        Long memberId;  // 추가하려는 멤버의 id
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class updateProjectDTO{
        Optional<String> title;
        Optional<String> content;
        Optional<LocalDate> deadline;
        Optional<String> projectStatus;

        List<String> skillTagList;
        List<String> positionList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class deleteMemberFromProjectDTO{
        Long projectId;
        Long memberId;  // 삭제하려는 멤버의 id
    }
}
