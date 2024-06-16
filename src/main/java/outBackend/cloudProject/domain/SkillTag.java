package outBackend.cloudProject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SkillTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "skillTag", cascade = CascadeType.ALL)
    private List<MemberSkillTag> memberSkillTagList = new ArrayList<>();

    @OneToMany(mappedBy = "skillTag", cascade = CascadeType.ALL)
    private List<ProjectSkillTag> projectSkillTagList = new ArrayList<>();
}
