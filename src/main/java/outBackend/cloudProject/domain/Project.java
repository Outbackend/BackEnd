package outBackend.cloudProject.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity(name = "project")
@Getter
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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectPosition> projectPositionList ;

    @Builder
    public Project(String title,String content,LocalDate deadline,List<ProjectPosition> projectPositionList){
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.projectPositionList = projectPositionList;
    }

    public void update(String title, String content, LocalDate deadline){
        this.title = title;
        this.content = content;
        this.deadline  = deadline;
    }

}



