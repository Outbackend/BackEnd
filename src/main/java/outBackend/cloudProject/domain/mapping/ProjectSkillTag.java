package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSkillTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "skillTag_id")
    private SkillTag skillTag;

    public void setProject(Project project) {
        if(this.project != null){
            this.project.getProjectSkillTagList().remove(this);
        }
        this.project = project;
        project.getProjectSkillTagList().add(this);
    }

    public void setSkillTag(SkillTag skillTag) {
        if(this.skillTag != null){
            this.skillTag.getProjectSkillTagList().remove(this);
        }
        this.skillTag = skillTag;
        skillTag.getProjectSkillTagList().add(this);
    }
}
