package fr.eni.ecole.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.dal.DALException;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	// --- 1 | Créer les requêtes pour les objets enchères
	
	private static final String SQL_SELECT_ENCHERE = "SELECT UTILISATEURS.no_utilisateur, credit, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE no_article = ? ";
	private static final String SQL_INSERT_ENCHERE = null; // première enchère
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
	
	
}
