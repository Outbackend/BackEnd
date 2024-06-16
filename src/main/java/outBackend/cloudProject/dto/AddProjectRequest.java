package outBackend.cloudProject.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddProjectRequest {
    private String title;
    private String content;
    private LocalDate deadline;
//    private List<ProjectPosition> projectPositionList;

    public Project toEntity(){
        return Project.builder()
                .title(title)
                .content(content)
                .deadline(deadline)
//                .projectPositionList(projectPositionList)
                .build();
    }

}
