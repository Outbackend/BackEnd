package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.MemberResponseDTO;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectConverter {
    public static Project toproject(ProjectRequestDTO.SaveDTO request) {
        return Project.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .projectSkillTagList(new ArrayList<>())
                .projectPositionList(new ArrayList<>())
                .build();
    }

    public static ProjectResponseDTO.SaveResultDTO toaddResultDTO(Project project) {
        return ProjectResponseDTO.SaveResultDTO.builder()
                .title(project.getTitle())
                .content(project.getContent())
                .deadline(project.getDeadline())
                .build();
    }

    public static ProjectResponseDTO.ProjectPageDTO toUserPageDTO(Project project) {

        List<String> skillTagList = project.getProjectSkillTagList().stream()
                .map(projectSkillTag -> {
                    return projectSkillTag.getSkillTag().getName();
                }).collect(Collectors.toList());

        List<String> positionList = project.getProjectPositionList().stream()
                .map(projectPosition -> {
                    return projectPosition.getPosition().getName();
                }).collect(Collectors.toList());

        return ProjectResponseDTO.ProjectPageDTO.builder()
                .title(project.getTitle())
                .content(project.getContent())
                .deadline(project.getDeadline())
                .SkillTagList(skillTagList)
                .PositionList(positionList)
                .build();
    }
}
