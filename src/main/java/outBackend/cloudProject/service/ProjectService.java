package outBackend.cloudProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.AddProjectRequest;
import outBackend.cloudProject.dto.UpdateProjectRequest;
import outBackend.cloudProject.repository.ProjectRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project save(AddProjectRequest request){
        return projectRepository.save(request.toEntity());
    }
    public List<Project> findAll(){
        return projectRepository.findAll();
    }
    public Project findById(long id){
        return projectRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }
    public void delete(long id){
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project update(long id, UpdateProjectRequest request){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        project.update(request.getTitle(),request.getContent(), request.getDeadline());

        return project;
    }
}
