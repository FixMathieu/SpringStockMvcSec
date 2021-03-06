package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;


@Controller
public class ArticleController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/articles")
	public String articles(Model model,@RequestParam(name="page", defaultValue="0")int page,
									@RequestParam(name="keyword", defaultValue="")String kw,
									@RequestParam(name="catId",defaultValue="1")long catId){
		Page<Article> articles= articleRepository.findByDescriptionContains(kw,PageRequest.of(page, 5));
		Page<Article> articlesCat=articleRepository.findByCategoryId(catId,PageRequest.of(page, 5));
		List<Category> category= categoryRepository.findAll();
		model.addAttribute("listCategory",category);
		model.addAttribute("category",articlesCat);
		model.addAttribute("listArticle",articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage",page);
		return "articles";
	}

	
	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);
		return "redirect:/articles?page="+page+"&keyword="+keyword;
	}
	@PostMapping("/save")
	public String save( @Valid  Article article, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return "article";
		articleRepository.save(article);
		return"redirect:/articles";
	}
	@GetMapping("/article")
	public String article(Model model) {
		model.addAttribute("article",new Article());
		return "article";
	}
	@GetMapping("/edit")
	public String edit(Long id, Model model) {
	Article article = articleRepository.findById(id).get();
	model.addAttribute("article", article);
		return "edit";
		
	}
	@PostMapping("/update")
	public String update(@Valid  Article article, BindingResult bindingResult){
		if(bindingResult.hasErrors()) return "article";
		articleRepository.save(article);
		return"redirect:/articles";
	}

}
