package outBackend.cloudProject.dto;

import lombok.Getter;
import outBackend.cloudProject.domain.Project;

@Getter
public class ProjectResponse {

    private final String title;
    private final String content;
    private int front_recruit_count;
    private int front_current_count;
    private int back_recruit_count;
    private int back_current_count;
    private int design_recruit_count;
    private int design_current_count;

    public ProjectResponse(Project project){
        this.title = project.getTitle();
        this.content = project.getContent();
        front_recruit_count = project.getFront_recruit_count();
        front_current_count = project.getFront_current_count();
        back_recruit_count = project.getBack_recruit_count();
        back_current_count = project.getBack_current_count();
        design_recruit_count = project.getDesign_recruit_count();
        design_current_count = project.getDesign_current_count();
    }
}
