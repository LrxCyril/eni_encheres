package fr.eni.ecole.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	//creer constante de requete d'insertion d'un article
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	
	@Override
	public void InsertArticles(Article nouvelArticle) throws DALException {
		//creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur DAO JdBC Impl
		String date = null;
		//--- Obtenir la requête
		try(Connection connexion = ConnectionProvider.getConnection();){
			
		//--- Construire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_INSERT_ARTICLE);
			ordre.setInt(1,nouvelArticle.getNoArticle());
			ordre.setString(2,nouvelArticle.getNomArticle());
			ordre.setString(3,nouvelArticle.getDescription());
			
			ordre.setDate(4,java.sql.Date.valueOf(date));
			ordre.setDate(5,java.sql.Date.valueOf(date));
			ordre.setInt(6,nouvelArticle.getPrixInitial());
			ordre.setInt(7,nouvelArticle.getPrixVente());
			ordre.setInt(8,nouvelArticle.getNoUtilisateur());
			ordre.setInt(9,nouvelArticle.getNoCategorie());
			
		//--- Exécuter la requête
			ordre.executeUpdate();
			
		//--- Fermer la connexion
			connexion.close();
		} catch (SQLException sqle) {
			// Levée de l'exception pas d'article
			throw new DALException("Insert invalide !");
		}
	}

	@Override
	public List<ArticleVendu> SelectArticle(LocalDate date) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
