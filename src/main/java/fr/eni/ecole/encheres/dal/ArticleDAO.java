package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.Utilisateur;

public interface ArticleDAO {

	void InsertArticles(Article nouvelArticle) throws DALException;
}
