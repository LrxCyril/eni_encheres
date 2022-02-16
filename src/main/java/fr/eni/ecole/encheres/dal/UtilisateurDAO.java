package fr.eni.ecole.encheres.dal;

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
	Utilisateur VerifUtilisateurIdentifiant(String identifiant, String motdepasse) throws DALException;
	String VerifIdentifiantExistant(String email,String pseudo) throws DALException ;
	void InsertUtilisateur(Utilisateur nouvelUtilisateur) throws DALException;
}
