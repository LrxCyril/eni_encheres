package fr.eni.ecole.encheres.dal;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Categorie;

public interface ArticleDAO {

	void insertArticles(Article nouvelArticle) throws DALException;
	List<ArticleVendu> selectArticle(LocalDate date) throws DALException;
	List<Categorie> selectLibelle() throws DALException;
	List<ArticleVendu> selectArticlebyNom(LocalDate date, String recherche) throws DALException;
	List<ArticleVendu> selectArticlebyCate(LocalDate date, int filtreCategorie) throws DALException;
	List<ArticleVendu> selectArticlebyCateNom(LocalDate date, int filtreCategorie, String recherche)throws DALException;
	void MiseAJourArticle(Article article, Connection cnx) throws DALException;
	
}
