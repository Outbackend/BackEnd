//package outBackend.cloudProject.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import outBackend.cloudProject.entity.Comment;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class CommentDto {
//    private Long id;
//    //private Long projectId;
//    private Long memberId;
//    private String body;
//
//    public static CommentDto createCommentDto(Comment comment) {
//        return new CommentDto(
//                comment.getId(),
//                //comment.getProject().getId(),
//                comment.getMember().getId(),
//                comment.getBody()
//        );
//    }
//}
