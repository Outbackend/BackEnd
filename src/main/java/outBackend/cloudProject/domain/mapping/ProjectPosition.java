package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;

@Entity
@Table(name = "project_position")
@Getter
@Setter
public class ProjectPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;


}