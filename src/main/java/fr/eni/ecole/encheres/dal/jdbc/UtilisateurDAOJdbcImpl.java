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
	private static final String SQL_SELECT_NOM = "SELECT nom FROM UTILISATEURS WHERE pseudo=? or email=?";
	private static final String SQL_SELECT_IDENTIFIANT = "SELECT no_utilisateur,pseudo,nom,prenom,telephone,email,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo=? or email=? and mot_de_passe=?";
	private static final String SQL_SELECT_PSEUDO = "SELECT no_utilisateur,pseudo,nom,prenom,telephone,email,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo=? or email=?";
	private static final String SQL_INSERT_UTILISATEURS = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,rue,code_postal,ville,mot_de_passe,credit,administrateur,telephone) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo=?,nom=?,prenom=?,email=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=?,telephone=?  WHERE  no_utilisateur=?";

	
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
			//Appel de la methode constuisant l'utilisateur
			utilisateurConnecte=lireEtCreerUtilisateur(utilisateurConnecte, connexion, ordre);
			connexion.close();
		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return utilisateurConnecte;
	}

	private Utilisateur lireEtCreerUtilisateur(Utilisateur utilisateurConnecte, Connection connexion, PreparedStatement ordre)
			throws SQLException {
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
		utilisateurConnecte.setTelephone(rs.getString("telephone"));
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
		}
		
		return utilisateurConnecte;
	}

	@Override
	public String VerifIdentifiantExistant(String email, String pseudo) throws DALException {
		String NomUtilisateur = null;
		  try {
		  	//---  1- Obtenir une connexion
			Connection connexion = ConnectionProvider.getConnection();
			//---  2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_NOM);
			//--- 3- Exécuter la requête
			ResultSet rs =ordre.executeQuery();
			//--- 4- Si la connexion est bien existante..
			if (rs.next()) {
				NomUtilisateur = (rs.getString("nom"));
			}
			//--- 5- Fermer la connexion
			connexion.close();
			//--- 6- Gérer l'exception
		} catch (SQLException sqle){
				//Levé de l'exception pas de Nom
				throw new DALException("Pas de nom d'utilisateur !");
		}
		   //--- 7- Retour de Nom 
	return NomUtilisateur;
		
	}

	@Override
	public void InsertUtilisateur(Utilisateur nouvelUtilisateur) throws DALException {
		
		try {
		//---  1- Obtenir une connexion
		Connection connexion = ConnectionProvider.getConnection();
		//---  2- Contruire la requete
		PreparedStatement ordre = connexion.prepareStatement(SQL_INSERT_UTILISATEURS);
		ordre.setString(1,nouvelUtilisateur.getPseudo());
		ordre.setString(2,nouvelUtilisateur.getNom());
		ordre.setString(3,nouvelUtilisateur.getPrenom());
		ordre.setString(4,nouvelUtilisateur.getEmail());
		ordre.setString(5,nouvelUtilisateur.getRue());
		ordre.setString(6,nouvelUtilisateur.getCodePostal());
		ordre.setString(7,nouvelUtilisateur.getVille());
		ordre.setString(8,nouvelUtilisateur.getMotDePasse());
		ordre.setInt(9,nouvelUtilisateur.getCredit());
		if(nouvelUtilisateur.isAdministrateur()) {
			ordre.setInt(10, 1);
		} else { 
			ordre.setInt(10, 0);
		}
		ordre.setString(11,nouvelUtilisateur.getTelephone());
		ordre.executeUpdate();
		//--- 5- Fermer la connexion
		connexion.close();
		} catch (SQLException sqle){
			//Levé de l'exception pas de Nom
			throw new DALException("Insert invalide !");
	}
	}


	@Override
	public Utilisateur lireUtilisateurPseudo(String pseudo) throws DALException {
		Utilisateur utilisateurConnecte= new Utilisateur();
		//Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		try {
			// 1- Obtenir une connexion
			Connection connexion = ConnectionProvider.getConnection();
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_PSEUDO);
			// ajout du paramètre à la requete(Where pseudo)
			ordre.setString(1,pseudo.trim());
			ordre.setString(2,pseudo.trim());
			//Appel de la methode constuisant l'utilisateur
			utilisateurConnecte=lireEtCreerUtilisateur(utilisateurConnecte, connexion, ordre);
			connexion.close();
		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return utilisateurConnecte;
	}
	@Override
	public void MajUtilisateur(Utilisateur utilisateur) throws DALException {
		//Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		try {
			// 1- Obtenir une connexion
			Connection connexion = ConnectionProvider.getConnection();
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_UPDATE_UTILISATEUR);
			ordre.setString(1,utilisateur.getPseudo());
			ordre.setString(2,utilisateur.getNom());
			ordre.setString(3,utilisateur.getPrenom());
			ordre.setString(4,utilisateur.getEmail());
			ordre.setString(5,utilisateur.getRue());
			ordre.setString(6,utilisateur.getCodePostal());
			ordre.setString(7,utilisateur.getVille());
			ordre.setString(8,utilisateur.getMotDePasse());
			ordre.setInt(9,utilisateur.getCredit());
			if(utilisateur.isAdministrateur()) {
				ordre.setInt(10, 1);
			} else { 
				ordre.setInt(10, 0);
			}
			ordre.setString(11,utilisateur.getTelephone());
			//--- 3- Exécuter la requête
			ordre.setInt(12,utilisateur.getNo_utilisateur());	
			ordre.executeUpdate();

			connexion.close();

		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de mettre à jour la ligne");
		}
	}
}
