package fr.eni.ecole.encheres.dal.jdbc;

import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.dal.DALException;

public interface EnchereDAO {

	// TODO : methodes liées à EnchereDAOJdbcImpl
	
	ObjetEnchere premiereEnchere(int noArticle) throws DALException;
	
	void debiterAcheteur(int noUtilisateur, int nouveauCreditAcheteur);

	void crediterAcheteur(int noUtilisateur, int nouveauCreditVendeur);

	void meilleureOffre(int noUtilisateur, int montantEnchere);

	void nouveauPrixVente(int noUtilisateur, int montantEnchere);
	
	
}
