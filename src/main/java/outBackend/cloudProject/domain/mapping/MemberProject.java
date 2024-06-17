package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public void setMember(Member member){

        if(this.member != null){
            this.member.getMemberProjectList().remove(this);
        }
        this.member = member;
        member.getMemberProjectList().add(this);
    }

    public void setProject(Project project){

        if(this.project != null){
            this.project.getMemberProjectList().remove(this);
        }
        this.project = project;
        project.getMemberProjectList().add(this);
    }
}
