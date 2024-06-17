package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.MemberConverter;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.MemberResponseDTO;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;
import outBackend.cloudProject.repository.ProjectRepository;
import outBackend.cloudProject.service.projectService.ProjectService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProjectApiController {
    private final ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @PostMapping("/api/projects")
    public ApiResponse<ProjectResponseDTO.SaveResultDTO> add(@RequestBody ProjectRequestDTO.SaveDTO request){
        Project project = projectService.save(request);
        return ApiResponse.onSuccess(ProjectConverter.toaddResultDTO(project));
    }

    @GetMapping("/api/projects/{project_id}")
    public ApiResponse<ProjectResponseDTO.ProjectPageDTO> userPage(@PathVariable Long project_id){

        Project project = projectService.projectInfo(project_id);
        return ApiResponse.onSuccess(ProjectConverter.toUserPageDTO(project));
    }
}
