package outBackend.cloudProject.dto;

import lombok.*;

public class CommentResponseDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    @ToString
    public class SaveResultDTO {
        Long id;
        Long member_id;
        Long project_id;
        String body;
    }
}
