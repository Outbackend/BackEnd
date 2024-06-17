package outBackend.cloudProject.dto;

import lombok.*;

public class CommentResponseDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    @ToString
    public static class SaveResultDTO {
        Long id;
        Long member_id;
        Long project_id;
        String body;
    }
}
