package outBackend.cloudProject.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import outBackend.cloudProject.domain.Article;
import outBackend.cloudProject.dto.AddArticleRequest;
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
}
