package fr.eni.ecole.encheres.dal;

import java.sql.Connection;
import java.util.List;

import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.ObjetEnchere;

public interface EnchereDAO {

	// TODO : methodes liées à EnchereDAOJdbcImpl
	
	ObjetEnchere premiereEnchere(int noArticle) throws DALException;

	Enchere enchereExistante(int noArticle) throws DALException;

	void InsertEnchere(Enchere derniereEnchere, Connection cnx) throws DALException;
	
	
}
