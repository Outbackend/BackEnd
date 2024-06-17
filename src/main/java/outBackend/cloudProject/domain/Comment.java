package outBackend.cloudProject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import outBackend.cloudProject.dto.CommentDTO;

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

    public static Comment createComment(CommentDTO dto, Project project, Member member) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if (dto.getProjectId() != project.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글 id가 잘못됐습니다.");
        }
        return new Comment(
                dto.getId(),
                member,
                project,
                dto.getBody()
        );
    }

    public void patch(CommentDTO dto, Long memberId) {
        if(this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        }

        //.getMember()가 null 일 수 있음
        if(this.getMember().getId() != memberId) {
            throw new IllegalArgumentException("댓글 수정 실패! 회원님이 작성한 댓글이 아닙니다.");
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }

}
