package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public class CommentRequestDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    public class SaveDTO {
        Long id;
        Long member_id;
        Long project_id;
        String body;
    }
}
