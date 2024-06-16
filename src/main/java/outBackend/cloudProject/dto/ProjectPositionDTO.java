package outBackend.cloudProject.dto;

import lombok.Data;
import lombok.Getter;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.ProjectPosition;

@Data
@Getter
public class ProjectPositionDTO {
    private Long id;
    private String name;

    public ProjectPositionDTO (ProjectPosition projectPosition){
        this.id = projectPosition.getId();
        this.name = projectPosition.getName();
    }
}
