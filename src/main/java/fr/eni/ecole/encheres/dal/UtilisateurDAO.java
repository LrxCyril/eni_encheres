package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Interface definissant les methodes de requettage dans la table utilisateur
 * @author slouerat2021
 *
 */
public interface UtilisateurDAO {
	
	Utilisateur VerifUtilisateur(String identifiant) throws DALException;

}
