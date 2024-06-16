package outBackend.cloudProject.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.ProjectPosition;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ProjectResponse {

    private final String title;
    private final String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate deadline;
    private List<ProjectPosition> projectPositionList;

    public ProjectResponse (Project project){
        this.title = project.getTitle();
        this.content = project.getContent();
        this.deadline = project.getDeadline();
        this.projectPositionList = project.getProjectPositionList();
    }
}
