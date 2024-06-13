package outBackend.cloudProject.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import outBackend.cloudProject.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    private Integer front_recruit_count;
    private Integer front_current_count;
    private  Integer back_recruit_count;
    private  Integer back_current_count;
    private Integer design_recruit_count;
    private Integer design_current_count;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .front_recruit_count(front_recruit_count)
                .front_current_count(front_current_count)
                .back_recruit_count(back_recruit_count)
                .back_current_count(back_current_count)
                .design_recruit_count(design_recruit_count)
                .design_current_count(design_current_count)
                .build();
    }
}
