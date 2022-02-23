package fr.eni.ecole.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	// --- 1 | Créer les requêtes pour les objets enchères
	
	private static final String SQL_SELECT_ENCHERE = "SELECT UTILISATEURS.no_utilisateur, credit, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE no_article = ? ";
	private static final String SQL_SELECT_ENCHERE_EXIST = "SELECT TOP 1 no_enchere, date_enchere, montant_enchere,ENCHERES.no_article, ENCHERES.no_utilisateur, credit, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.prix_vente FROM ENCHERES inner Join UTILISATEURS on ENCHERES.no_utilisateur =UTILISATEURS.no_utilisateur inner Join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article WHERE ENCHERES.no_article=? ORDER BY montant_enchere DESC;";
	private static final String SQL_INSERT_ENCHERE = "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) values (?,?,?,?)"; // première enchère
	private static final String SQL_UPDATE_ENCHERE = null; // surenchérir
	

	@Override
	public ObjetEnchere premiereEnchere(int noArticle) throws DALException {
		
		ObjetEnchere enchereInitiale = null;

		// --- 1 | Obtenir une connexion
		try (Connection cnx = ConnectionProvider.getConnection();){
		
		// --- 2 | Construire la requête
			PreparedStatement ordre = cnx.prepareStatement(SQL_SELECT_ENCHERE);
		
		// --- 3 | Ajouter le paramètre à la requête (Where...)
			ordre.setInt(1, noArticle);
		
		// --- 4 | Appeler la méthode construisant l'enchère
			ResultSet rs = ordre.executeQuery(); 
		
		// --- 5 | Vérifier si la connexion est existante
			if (rs.next()) {
				enchereInitiale = new ObjetEnchere(rs.getInt("no_utilisateur"), rs.getInt("credit"), rs.getInt("prix_initial"), rs.getInt("prix_vente"));
			}
			
		} catch (SQLException e) {
			// --- 6 | Lever l'exception : le numéro utilisateur n'existe pas
			e.printStackTrace();
			throw new DALException("le numéro article n'existe pas");
		}
		return enchereInitiale;
	}
	
	
	@Override
	public Enchere enchereExistante(int noArticle) throws DALException {
		Enchere encherePrecedente = null;
		// --- 1 | Obtenir une connexion
		try (Connection cnx = ConnectionProvider.getConnection();){
		// --- 2 | Construire la requête
			PreparedStatement ordre = cnx.prepareStatement(SQL_SELECT_ENCHERE_EXIST);
		// --- 3 | Ajouter le paramètre à la requête (Where...)
			ordre.setInt(1, noArticle);
		// --- 4 | Appeler la méthode construisant l'enchère
			ResultSet rs = ordre.executeQuery(); 
		// --- 5 | Vérifier si la connexion est existante
			if (rs.next()) {
				//construction des objets a retourner
				Utilisateur encherisseur = new Utilisateur();
				encherisseur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				encherisseur.setCredit(rs.getInt("credit"));
				Article article = new Article();
				article.setNoArticle(rs.getInt("no_article"));
				article.setPrixInitial(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));	
				encherePrecedente = new Enchere(noArticle,LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()), rs.getInt("montant_enchere"));
				encherePrecedente.setArticle(article);
				encherePrecedente.setUtilisateur(encherisseur);
			}
		} catch (SQLException e) {
			// --- 6 | Lever l'exception : le numéro utilisateur n'existe pas
			e.printStackTrace();
			throw new DALException("le numéro article n'existe pas");
		}
		return encherePrecedente;
	}


	@Override
	public  void InsertEnchere(Enchere derniereEnchere, Connection cnx)throws DALException {
		
		try{
			PreparedStatement ordre = cnx.prepareStatement(SQL_INSERT_ENCHERE);
			ordre.setTimestamp(1,Timestamp.valueOf(derniereEnchere.getDateEnchere()));
			ordre.setInt(2,derniereEnchere.getMontantEnchere());
			ordre.setInt(3,derniereEnchere.getArticle().getNoArticle());
			ordre.setInt(4,derniereEnchere.getUtilisateur().getNoUtilisateur());
			
			ordre.executeUpdate();
			//--- 5- Fermer la connexion
			} catch (SQLException sqle){
				sqle.printStackTrace();
				//Levé de l'exception pas de Nom
				throw new DALException("Insert invalide !");
			}

	
	}
}
