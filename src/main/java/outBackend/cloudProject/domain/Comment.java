package outBackend.cloudProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.CommentDto;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="member_id")
//    private Project project;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    @Column
    private String body;

//    public void patch(CommentDto dto) {
//        if(this.id != dto.getId()) {
//            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
//        }
//
//        //.getMember()가 null 일 수 있음
//        if(this.getMember().getId() != dto.getMember().getId()) {
//            throw new IllegalArgumentException("댓글 수정 실패! 회원님이 작성한 댓글이 아닙니다.");
//        }
//        if (dto.getBody() != null) {
//            this.body = dto.getBody();
//        }
//    }

//    public static Comment createComment(CommentDto dto, Project project) {
//        if (dto.getId() != null) {
//            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
//        }
//        if (dto.getProjectId() != project.getId()) {
//            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
//        }
//        return new Comment(
//                dto.getId(),
//                //project,
//                dto.getMember().getId(), //이게되는지 확인
//                dto.getBody()
//        );
//    }
}
