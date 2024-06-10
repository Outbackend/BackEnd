package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.SkillTag;

import java.util.Optional;

public interface SkillTagRepository extends JpaRepository<SkillTag, Long> {

    Optional<SkillTag> findByName(String name);
}
