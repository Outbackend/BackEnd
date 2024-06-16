package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import outBackend.cloudProject.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    //특정 게시글의 모든 댓글 조회
//    List<Comment> findByProjectId(Long projectId);

    //특정 아이디의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE member_id = :memberId",
            nativeQuery = true)
    List<Comment> findByMemberId(Long memberId);

}
