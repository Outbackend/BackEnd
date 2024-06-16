package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
