package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;

public class IBusinessImpl implements IBusiness{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private  ArticleRepository articleRepository;
	
	public List<Article> readArticles() {
		
		return  articleRepository.findAll();
	}
}
