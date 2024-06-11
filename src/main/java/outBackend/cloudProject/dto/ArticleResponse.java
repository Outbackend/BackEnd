package outBackend.cloudProject.dto;

import lombok.Getter;
import outBackend.cloudProject.domain.Article;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;
    private int front_recruit_count;
    private int front_current_count;
    private int back_recruit_count;
    private int back_current_count;
    private int design_recruit_count;
    private int design_current_count;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
        front_recruit_count = article.getFront_recruit_count();
        front_current_count = article.getFront_current_count();
        back_recruit_count = article.getBack_recruit_count();
        back_current_count = article.getBack_current_count();
        design_recruit_count = article.getDesign_recruit_count();
        design_current_count = article.getDesign_current_count();
    }
}
