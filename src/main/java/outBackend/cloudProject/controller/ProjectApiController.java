package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.AddProjectRequest;
import outBackend.cloudProject.dto.ProjectResponse;
import outBackend.cloudProject.dto.UpdateProjectRequest;
import outBackend.cloudProject.service.ProjectService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProjectApiController {
    private final ProjectService projectService;

    @PostMapping("/api/projects")
    public ResponseEntity<Project> addProject(@RequestBody AddProjectRequest request){
        Project savedProject = projectService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProject);
    }

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectResponse>> findAllProjects(){
        List<ProjectResponse> projects = projectService.findAll()
                .stream()
                .map(ProjectResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(projects);
    }

    @GetMapping("/api/projects/{id}")
    public ResponseEntity<ProjectResponse> findProject(@PathVariable(name = "id") long id){
        Project project = projectService.findById(id);

        return ResponseEntity.ok()
                .body(new ProjectResponse(project));
    }

    @DeleteMapping("/api/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable(name = "id") long id){
        projectService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(name = "id") long id, @RequestBody UpdateProjectRequest request){
        Project updatedProject = projectService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedProject);
    }
}
