package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    public void setProject(Project project) {
        if(this.project != null){
            this.project.getProjectPositionList().remove(this);
        }
        this.project = project;
        project.getProjectPositionList().add(this);
    }

    public void setPosition(Position position) {
        if(this.position != null){
            this.position.getProjectPosition().remove(this);
        }
        this.position = position;
        position.getProjectPosition().add(this);
    }
}
