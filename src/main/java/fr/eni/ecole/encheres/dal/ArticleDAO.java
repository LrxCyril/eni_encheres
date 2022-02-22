package fr.eni.ecole.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;

public interface ArticleDAO {

	void insertArticles(Article nouvelArticle) throws DALException;
	List<ArticleVendu> selectArticle(LocalDate date) throws DALException;
	List<String> selectLibelle() throws DALException;
	List<ArticleVendu> selectArticlebyNom(LocalDate date, String recherche) throws DALException;
	List<ArticleVendu> selectArticlebyCate(LocalDate date, String filtreCategorie) throws DALException;
	List<ArticleVendu> selectArticlebyCateNom(LocalDate date, String filtreCategorie, String recherche)throws DALException;
	
}
