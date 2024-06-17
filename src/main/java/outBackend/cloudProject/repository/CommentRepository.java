package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import outBackend.cloudProject.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment WHERE project_id = :projectId",
            nativeQuery = true)
    List<Comment> findByProjectId(Long projectId);
}
