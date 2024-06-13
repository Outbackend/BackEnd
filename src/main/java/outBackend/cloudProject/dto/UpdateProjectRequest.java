package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProjectRequest {
    private String title;
    private String content;
    private Integer front_recruit_count;
    private Integer front_current_count;
    private Integer back_recruit_count;
    private Integer back_current_count;
    private Integer design_recruit_count;
    private Integer design_current_count;
}
