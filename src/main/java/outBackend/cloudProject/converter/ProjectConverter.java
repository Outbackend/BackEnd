package outBackend.cloudProject.converter;

import org.springframework.security.core.Authentication;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.dto.MemberResponseDTO;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
