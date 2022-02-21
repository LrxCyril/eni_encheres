package fr.eni.ecole.encheres.dal.jdbc;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	//creer constante de requete d'insertion d'un article
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	
	@Override
	public void InsertArticles(Article nouvelArticle) throws DALException {
		//creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur DAO JdBC Impl
		
	}

	
}
