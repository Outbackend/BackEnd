package outBackend.cloudProject.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    @Builder
    public Project(Long id, String title, String content, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deadline = deadline;

    }
    public void update(String title, String content, LocalDate deadline){
        this.title = title;
        this.content = content;
        this.deadline = deadline;
    }

}



