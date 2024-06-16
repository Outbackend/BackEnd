package outBackend.cloudProject.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import outBackend.cloudProject.domain.Project;

import java.time.LocalDate;

@Getter
public class ProjectResponse {

    private final String title;
    private final String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate deadline;

    public ProjectResponse(Project project){
        this.title = project.getTitle();
        this.content = project.getContent();
        this.deadline = project.getDeadline();
    }
}
