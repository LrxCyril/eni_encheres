package fr.eni.ecole.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Utilisateur;

public interface ArticleDAO {

	void InsertArticles(Article nouvelArticle) throws DALException;
	List<ArticleVendu> SelectArticle(LocalDate date) throws DALException;
	}
}
