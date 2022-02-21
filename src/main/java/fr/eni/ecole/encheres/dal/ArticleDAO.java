package fr.eni.ecole.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;

public interface ArticleDAO {

	void InsertArticles(Article nouvelArticle) throws DALException;
	List<ArticleVendu> SelectArticle(LocalDate date) throws DALException;
	
}
