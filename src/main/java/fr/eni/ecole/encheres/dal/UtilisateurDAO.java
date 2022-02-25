package fr.eni.ecole.encheres.dal;

import java.sql.Connection;

import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Interface definissant les methodes de requettage dans la table utilisateur
 * @author slouerat2021
 *
 */
public interface UtilisateurDAO {
	

	/**
	 * Methode permettant de recuperer un utilisateur selon son pseudo
	 */
	Utilisateur verifUtilisateurIdentifiant(String identifiant, String motdepasse) throws DALException;
	//String VerifIdentifiantExistant(String email,String pseudo) throws DALException ;
	void insertUtilisateur(Utilisateur nouvelUtilisateur) throws DALException;
	Utilisateur lireUtilisateurPseudo(String pseudo) throws DALException;
	void majUtilisateur(Utilisateur utilisateur)throws DALException;
	void supprimerUtilisateur(int noUtilisateur) throws DALException;
	void miseAJourCreditUtilisateur(Utilisateur encherisseur, Connection cnx) throws DALException;
}
