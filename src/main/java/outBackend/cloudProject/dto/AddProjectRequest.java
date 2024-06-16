package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.Project;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddProjectRequest {
    private String title;
    private String content;
    private LocalDate deadline;

    public Project toEntity(){
        return Project.builder()
                .title(title)
                .content(content)
                .deadline(deadline)
                .build();
    }
}
