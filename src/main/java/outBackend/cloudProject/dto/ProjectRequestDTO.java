package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public class ProjectRequestDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public static class SaveDTO{
        String title;
        String content;
        LocalDate deadline;
        List<String> skillTagList;
    }
}
