package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.ecole.encheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.ecole.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
 * DAO factory permettant de creer des nouvelles instances de classe de requetage des BDD
 * @author slouerat2021
 *
 */
public class DAOFactory {
	
	/**
	 * Creation d'une instance de JDBC permettant de requeter la table utilisateur
	 * @return
	 * @throws DALException
	 */
	public static UtilisateurDAO getUtilisateurDAO() throws DALException {
		return new UtilisateurDAOJdbcImpl();
	}

	public static ArticleDAO getArticleDAO() throws DALException {
		return new ArticleDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() throws DALException{
		return new EnchereDAOJdbcImpl();
	}

}
