package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProjectRequest {
    private String title;
    private String content;
    private LocalDate deadline;
//    private List<ProjectPosition> projectPositionList;
}
