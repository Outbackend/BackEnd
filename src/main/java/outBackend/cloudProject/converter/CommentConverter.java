package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.CommentRequestDTO;
import outBackend.cloudProject.dto.CommentResponseDTO;

public class CommentConverter {
    public static Comment toComment(CommentRequestDTO.SaveDTO request) {
        return Comment.builder()
                .body(request.getBody())
                .build();
    }

    public static CommentResponseDTO.SaveResultDTO toaddResultDTO(Comment comment) {
        return CommentResponseDTO.SaveResultDTO.builder()
                .member_id(comment.getMember().getId())
                .project_id(comment.getProject().getId())
                .body(comment.getBody())
                .build();

    }
}
