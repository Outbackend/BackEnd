package outBackend.cloudProject.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.SkillTag;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberSkillTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skillTag_id")
    private SkillTag skillTag;

    public void setMember(Member member){

        if(this.member != null){
            this.member.getMemberSkillTagList().remove(this);
        }
        this.member = member;
        member.getMemberSkillTagList().add(this);
    }
}
