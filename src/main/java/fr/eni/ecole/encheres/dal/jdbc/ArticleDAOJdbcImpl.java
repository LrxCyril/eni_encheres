package fr.eni.ecole.encheres.dal.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;


public class ArticleDAOJdbcImpl implements ArticleDAO {
	//creer constante de requete d'insertion d'un article
	private static final String SQL_SELECT_ARTICLE ="SELECT no_article, nom_article, description, prix_initial, date_fin_encheres, pseudo from ARTICLES_VENDUS  Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur WHERE (date_debut_encheres>'?')";
	
	
	
	@Override
	public void InsertArticles(Article nouvelArticle) throws DALException {
		//creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur DAO JdBC Impl
		
	}

	@Override
	public List<ArticleVendu> SelectArticle(LocalDate date) throws DALException {
	
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); // on s'assure qu'il y a ait toujours une liste, même vide.
		ArticleVendu articleLu = null;
		
		//Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		// 1- Obtenir une connexion
		try(Connection connexion = ConnectionProvider.getConnection();) {
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE);
			// ajout du paramètre à la requete(Where pseudo)
			ordre.setDate(1,java.sql.Date.valueOf(date));
			//Appel de la methode constuisant l'utilisateur
			ResultSet rs =ordre.executeQuery();
			//si il y'a un resultat de requete
			if (rs.next()) {
			//Alimentation de l'instance d'utilisateur depuis les champs récupérés de la  requette
				articleLu.noArticle(rs.getInt("no_article"));
				articleLu.setnomArticle(rs.getInt("no_utilisateur"));
				articleLu.setdescription(rs.getString("pseudo"));
				articleLu.setmiseAPrix(rs.getString("nom"));
				articleLu.setdateFinEncheres(rs.getString("prenom"));
				articleLu.Utilisateur.setPseudo(rs.getString("email"));
				if (articleLu != null) { // TODO Tester si la prise en compte de null est nécessaire
					articles.add(articleLu);
				}

			}
			//5-fermeture de la connexion
		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return articles;
	}

	
}
