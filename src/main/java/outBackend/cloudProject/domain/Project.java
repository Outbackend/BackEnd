package outBackend.cloudProject.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String title;

    @Column(name = "body")
    private String content;

    @Column(name = "front_recruit_count")
    private Integer front_recruit_count;

    @Column(name = "front_current_count")
    private Integer front_current_count;

    @Column(name = "back_recruit_count")
    private Integer back_recruit_count;

    @Column(name = "back_current_count")
    private Integer back_current_count;

    @Column(name = "design_recruit_count")
    private Integer design_recruit_count;

    @Column(name = "design_current_count")
    private Integer design_current_count;

    @Builder
    public Project(Long id, String title, String content, Integer front_recruit_count, Integer front_current_count
            , Integer back_recruit_count, Integer back_current_count, Integer design_recruit_count, Integer design_current_count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.front_recruit_count = front_recruit_count;
        this.front_current_count = front_current_count;
        this.back_recruit_count = back_recruit_count;
        this.back_current_count = back_current_count;
        this.design_recruit_count = design_recruit_count;
        this.design_current_count = design_current_count;
    }
    public void update(String title, String content, Integer front_recruit_count, Integer front_current_count
            , Integer back_recruit_count, Integer back_current_count, Integer design_recruit_count, Integer design_current_count){
        this.title = title;
        this.content = content;
        this.front_recruit_count = front_recruit_count;
        this.front_current_count = front_current_count;
        this.back_recruit_count = back_recruit_count;
        this.back_current_count = back_current_count;
        this.design_recruit_count = design_recruit_count;
        this.design_current_count = design_current_count;
    }

}



