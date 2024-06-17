package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

public interface ProjectPositionRepository extends JpaRepository<ProjectPosition, Long> {
}
