package fr.eni.ecole.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

/**
 * Classe gerant les methodes de gestion d'un utilisateur
 * @author slouerat2021
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	private static final String SQL_SELECT_IDENTIFIANT = "SELECT no_utilisateur,pseudo,nom,prenom,email,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo=? or email=? and mot_de_passe=?";

	/**
	 * Methode permettant de recuperer un utilisateur selon son pseudo
	 */
	@Override
	public Utilisateur VerifUtilisateurIdentifiant(String identifiant, String motdepasse) throws DALException {

		Utilisateur utilisateurConnecte= new Utilisateur();
		
		//Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		try {
			// 1- Obtenir une connexion
			Connection connexion = ConnectionProvider.getConnection();
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_IDENTIFIANT);
			// ajout du paramètre à la requete(Where identifiant)
			ordre.setString(1,identifiant.trim());
			ordre.setString(2,identifiant.trim());
			ordre.setString(3,motdepasse.trim());
			// 3- Executer la requete
			ResultSet rs =ordre.executeQuery();
			//si il y'a un resultat de requete
			if (rs.next()) {
			//Alimentation de l'instance d'utilisateur depuis les champs récupérés de la  requette
			utilisateurConnecte.setNo_utilisateur(rs.getInt("no_utilisateur"));
			utilisateurConnecte.setPseudo(rs.getString("pseudo"));
			utilisateurConnecte.setNom(rs.getString("nom"));
			utilisateurConnecte.setPrenom(rs.getString("prenom"));
			utilisateurConnecte.setEmail(rs.getString("email"));
			utilisateurConnecte.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateurConnecte.setRue(rs.getString("rue"));
			utilisateurConnecte.setCodePostal(rs.getString("code_postal"));
			utilisateurConnecte.setVille(rs.getString("ville"));
			utilisateurConnecte.setCredit(rs.getInt("credit"));
			}
			// verification si admin et transformation en booleen
			switch (rs.getByte("administrateur")) {
			case 0:
				utilisateurConnecte.setAdministrateur(false);
				break;
			case 1:
				utilisateurConnecte.setAdministrateur(true);
				break;

			default:
				break;
				
			}
			connexion.close();
		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return utilisateurConnecte;
	}
	
	


}
