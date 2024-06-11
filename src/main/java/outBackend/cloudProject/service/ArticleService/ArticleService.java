package outBackend.cloudProject.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import outBackend.cloudProject.domain.Article;
import outBackend.cloudProject.dto.AddArticleRequest;
import outBackend.cloudProject.dto.UpdateArticleRequest;
import outBackend.cloudProject.repository.ArticleRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request){
        return articleRepository.save(request.toEntity());
    }
    public List<Article> findAll(){
        return articleRepository.findAll();
    }
    public Article findById(long id){
        return articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }
    public void delete(long id){
        articleRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(),request.getContent(), request.getFront_recruit_count(), request.getFront_current_count()
        , request.getBack_recruit_count(), request.getBack_current_count(), request.getDesign_recruit_count(), request.getDesign_current_count());

        return article;
    }
}
