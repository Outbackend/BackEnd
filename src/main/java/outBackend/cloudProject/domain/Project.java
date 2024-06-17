package outBackend.cloudProject.domain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.domain.mapping.ProjectPosition;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "deadline")
    private LocalDate deadline;

    @OneToMany(mappedBy = "project")
    private List<ProjectSkillTag> projectSkillTagList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectPosition> projectPositionList = new ArrayList<>();

    @OneToMany(mappedBy = "project_id")
    private List<MemberProject> memberProjectList = new ArrayList<>();
}



