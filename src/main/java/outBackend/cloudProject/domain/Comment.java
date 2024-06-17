package outBackend.cloudProject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@ToString
@DynamicUpdate
@DynamicInsert
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

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
