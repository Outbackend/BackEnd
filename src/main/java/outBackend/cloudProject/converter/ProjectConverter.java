package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;

import java.util.ArrayList;

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
}
