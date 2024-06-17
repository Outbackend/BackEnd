package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.mapping.MemberProject;

public interface MemberProjectRepository extends JpaRepository<MemberProject, Long> {
}
