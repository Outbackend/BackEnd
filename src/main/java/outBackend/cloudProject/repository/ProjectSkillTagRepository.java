package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;

public interface ProjectSkillTagRepository extends JpaRepository<ProjectSkillTag, Long> {
}
