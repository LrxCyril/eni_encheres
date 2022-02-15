package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Interface definissant les methodes de requettage dans la table utilisateur
 * @author slouerat2021
 *
 */
public interface UtilisateurDAO {
	
	Utilisateur VerifUtilisateurEmail(String identifiant) throws DALException;
	Utilisateur VerifUtilisateurPseudo(String identifiant) throws DALException;
}
