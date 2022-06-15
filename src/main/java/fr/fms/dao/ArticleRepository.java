package fr.fms.dao;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findByDescriptionContains(String description, Pageable pageable);
	public List<Article> deleteById(long id);
}
