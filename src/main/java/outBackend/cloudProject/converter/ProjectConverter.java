package outBackend.cloudProject.converter;

import outBackend.cloudProject.apiPayload.code.status.ErrorStatus;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.enums.ProjectStatus;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static outBackend.cloudProject.domain.enums.ProjectStatus.RECRUITING;

public class ProjectConverter {
    public static Project toproject(Member member, ProjectRequestDTO.SaveDTO request) {

        return Project.builder()
                .createrId(member.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .projectSkillTagList(new ArrayList<>())
                .projectPositionList(new ArrayList<>())
                .memberProjectList(new ArrayList<>())
                .build();
    }

    public static ProjectResponseDTO.SaveResultDTO toaddResultDTO(Project project) {
        return ProjectResponseDTO.SaveResultDTO.builder()
                .createrId(project.getCreaterId())
                .title(project.getTitle())
                .content(project.getContent())
                .deadline(project.getDeadline())
                .projectStatus(project.getProjectStatus())
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

    public static ProjectResponseDTO.addMemberToProjectResultDTO toAddMemberResultDTO(MemberProject memberProject){

        return ProjectResponseDTO.addMemberToProjectResultDTO.builder()
                .title(memberProject.getProject().getTitle())
                .projectId(memberProject.getProject().getId())
                .memberName(memberProject.getMember().getNickName())
                .memberId(memberProject.getMember().getId())
                .build();
    }

    public static ProjectResponseDTO.updateProjectResultDTO toUpdateProjectResultDTO(Project project){

        List<String> skillTagList = project.getProjectSkillTagList().stream()
                .map(projectSkillTag -> {
                    return projectSkillTag.getSkillTag().getName();
                }).collect(Collectors.toList());

        List<String> positionList = project.getProjectPositionList().stream()
                .map(projectPosition -> {
                    return projectPosition.getPosition().getName();
                }).collect(Collectors.toList());

        String projectStatus;
        if(project.getProjectStatus().equals(RECRUITING))
            projectStatus = "RECRUITING";
        else if(project.getProjectStatus().equals(ProjectStatus.IN_PROGRESS))
            projectStatus = "IN_PROGRESS";
        else
            projectStatus = "COMPLETED";

        return ProjectResponseDTO.updateProjectResultDTO.builder()
                .title(project.getTitle())
                .content(project.getContent())
                .deadline(project.getDeadline())
                .skillTagList(skillTagList)
                .positionList(positionList)
                .projectStatus(projectStatus)
                .build();
    }

    public static List<ProjectResponseDTO.showALLProjectResultDTO> toshowALLProjectResultDTO(List<Project> projectList) {
        return projectList.stream()
                .map(project -> ProjectResponseDTO.showALLProjectResultDTO.builder()
                        .title(project.getTitle())
                        .deadline(project.getDeadline())
                        .projectStatus(project.getProjectStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
