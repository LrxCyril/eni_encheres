package fr.eni.ecole.encheres.dal.jdbc;

import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.dal.DALException;

public interface EnchereDAO {

	// TODO : methodes liées à EnchereDAOJdbcImpl
	
	ObjetEnchere premiereEnchere(int noArticle) throws DALException;
	
	
}
