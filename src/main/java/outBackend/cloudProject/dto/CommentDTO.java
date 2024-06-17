package outBackend.cloudProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import outBackend.cloudProject.domain.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDTO {
    private Long id;
    private Long projectId;
    private Long memberId;
    private String body;

    public static CommentDTO createCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getProject().getId(),
                comment.getMember().getId(),
                comment.getBody()
        );
    }


}
