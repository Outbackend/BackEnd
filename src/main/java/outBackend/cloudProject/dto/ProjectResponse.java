package outBackend.cloudProject.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ProjectResponse {

    private final String title;
    private final String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate deadline;

//    private List<ProjectPosition> projectPositionList;
    public ProjectResponse(Project project){
        this.title = project.getTitle();
        this.content = project.getContent();
        this.deadline = project.getDeadline();
//        this.projectPositionList = project.getProjectPositionList();
    }
}
