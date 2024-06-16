package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;

public interface MemberSkillTagRepository extends JpaRepository<MemberSkillTag, Long> {
}
