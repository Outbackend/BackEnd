package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSkillTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skillTag_id")
    private SkillTag skillTag;



}
