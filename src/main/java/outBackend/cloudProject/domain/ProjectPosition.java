package outBackend.cloudProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;

@Entity
@Table(name = "project_position")
public class ProjectPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectPositionId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // Getters and setters
}