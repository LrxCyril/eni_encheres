package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.encheres.bo.Utilisateur;

public interface InsererUtilisateurDAO {
	int VerifIdentifiantExistant(String email,String pseudo) throws DALException ;
	Utilisateur InsertUtilisateur(Utilisateur nouvelUtilisateur) throws DALException;

	

}
