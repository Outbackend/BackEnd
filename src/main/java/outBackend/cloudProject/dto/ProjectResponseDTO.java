package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

public class ProjectResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveResultDTO {
        Long createrId;
        String title;
        String content;
        LocalDate deadline;
        ProjectStatus projectStatus;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPageDTO {
        String title;
        String content;
        LocalDate deadline;
        List<String> SkillTagList;
        List<String> PositionList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addMemberToProjectResultDTO{
        String title;
        Long projectId;
        String memberName;
        Long memberId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateProjectResultDTO{
        String title;
        String content;
        LocalDate deadline;
        String projectStatus;

        List<String> skillTagList;
        List<String> positionList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class showALLProjectResultDTO {
        String title;
        LocalDate deadline;
        ProjectStatus projectStatus;
    }
}
