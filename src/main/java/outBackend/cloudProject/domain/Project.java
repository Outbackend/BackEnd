package outBackend.cloudProject.domain;
import jakarta.persistence.*;
import lombok.*;
import outBackend.cloudProject.domain.mapping.ProjectPosition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
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

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ProjectPosition> projectPositionList = new ArrayList<>();

    @Builder
    public Project(String title,String content,LocalDate deadline){
        this.title = title;
        this.content = content;
        this.deadline = deadline;
//        this.projectPositionList = projectPositionList;
    }

    public void update(String title, String content, LocalDate deadline){
        this.title = title;
        this.content = content;
        this.deadline  = deadline;
    }

}



