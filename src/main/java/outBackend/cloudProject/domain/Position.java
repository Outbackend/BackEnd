package outBackend.cloudProject.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    private String position;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<ProjectPosition> projectPositionList;

    // Getters and setters
}