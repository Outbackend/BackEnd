package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProjectRequest {
    private String title;
    private String content;
    private LocalDate deadline;
}
