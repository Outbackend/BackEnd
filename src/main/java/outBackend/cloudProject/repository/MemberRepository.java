package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM comment WHERE member_id = :memberId",
            nativeQuery = true)
    List<Member> findByMemberId(Long memberId);
}
