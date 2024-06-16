package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;
import outBackend.cloudProject.service.projectService.ProjectService;


@RequiredArgsConstructor
@RestController
public class ProjectApiController {
    private final ProjectService projectService;

    @PostMapping("/api/projects")
    public ApiResponse<ProjectResponseDTO.SaveResultDTO> add(@RequestBody ProjectRequestDTO.SaveDTO request){
        Project project = projectService.save(request);
        return ApiResponse.onSuccess(ProjectConverter.toaddResultDTO(project));
    }

}
