package outBackend.cloudProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outBackend.cloudProject.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
