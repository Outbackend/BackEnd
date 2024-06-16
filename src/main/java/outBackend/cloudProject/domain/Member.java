package outBackend.cloudProject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import outBackend.cloudProject.domain.enums.Authority;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickName;

    @Column(unique = true)
    private String email;

    private String password;

    //  한 줄 소개글
    private String intro;
    //  설명
    private String about;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberSkillTag> memberSkillTagList = new ArrayList<>();


}
