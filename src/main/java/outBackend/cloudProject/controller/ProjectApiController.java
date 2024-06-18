package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;
import outBackend.cloudProject.repository.ProjectRepository;
import outBackend.cloudProject.service.projectService.ProjectService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectApiController {
    private final ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @PostMapping("/projects")
    public ApiResponse<ProjectResponseDTO.SaveResultDTO> addProject(@RequestBody ProjectRequestDTO.SaveDTO request,
                                                                    @RequestHeader("accessToken") String accessToken){
        Project project = projectService.save(accessToken, request);
        return ApiResponse.onSuccess(ProjectConverter.toaddResultDTO(project));
    }

    @GetMapping("/projects/{project_id}")
    public ApiResponse<ProjectResponseDTO.ProjectPageDTO> projectPage(@PathVariable Long project_id){

        Project project = projectService.projectInfo(project_id);
        return ApiResponse.onSuccess(ProjectConverter.toUserPageDTO(project));
    }

    @PostMapping("/projects/members")
    public ApiResponse<ProjectResponseDTO.addMemberToProjectResultDTO> addMemberToProject(@RequestHeader("accessToken") String accessToken,
                                                                                          @RequestBody ProjectRequestDTO.addMemberToProjectDTO request){

        MemberProject memberProject = projectService.addMember(accessToken, request);
        return ApiResponse.onSuccess(ProjectConverter.toAddMemberResultDTO(memberProject));
    }

    @DeleteMapping("/projects/{project_id}")
    public ApiResponse<String> deleteProject(@RequestHeader("accessToken") String accessToken,
                                             @PathVariable Long project_id){

        projectService.deleteProject(accessToken, project_id);
        return ApiResponse.onSuccess("Successfully deleted project");
    }

    @PatchMapping("/projects/{project_id}")
    public ApiResponse<ProjectResponseDTO.updateProjectResultDTO> updateProject(@RequestHeader("accessToken") String accessToken,
                                                                                @PathVariable Long project_id,
                                                                                @RequestBody ProjectRequestDTO.updateProjectDTO request){

        Project project = projectService.updateProject(accessToken, project_id, request);
        return ApiResponse.onSuccess(ProjectConverter.toUpdateProjectResultDTO(project));
    }

    @PatchMapping("/projects/members")
    public ApiResponse<String> deleteMemberFromProject(@RequestHeader("accessToken") String accessToken,
                                                       @RequestBody ProjectRequestDTO.deleteMemberFromProjectDTO request){

        projectService.removeMember(accessToken, request);
        return ApiResponse.onSuccess("Successfully deleted member from project");
    }

    @GetMapping("/projects")
    public ApiResponse<List<ProjectResponseDTO.showALLProjectResultDTO>> projectPage(){

        List<Project> projectList = projectService.projectInfoList();
        return ApiResponse.onSuccess(ProjectConverter.toshowALLProjectResultDTO(projectList));
    }

}
