package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.fms.dao.ArticleRepository;

import fr.fms.entities.Article;

public class IBusinessImpl implements IBusiness{
	
	

	@Autowired
	private  ArticleRepository articleRepository;
	
	public List<Article> readArticles() {
		
		return  articleRepository.findAll();
	}
}
