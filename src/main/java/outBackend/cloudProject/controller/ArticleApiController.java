package outBackend.cloudProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.domain.Article;
import outBackend.cloudProject.dto.AddArticleRequest;
import outBackend.cloudProject.dto.ArticleResponse;
import outBackend.cloudProject.dto.UpdateArticleRequest;
import outBackend.cloudProject.repository.ArticleRepository;
import outBackend.cloudProject.service.ArticleService.ArticleService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = articleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = articleService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable(name = "id") long id){
        Article article = articleService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable(name = "id") long id){
        articleService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(name = "id") long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = articleService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
