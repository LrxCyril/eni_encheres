package fr.eni.ecole.encheres.dal;

import java.sql.Connection;
import java.util.List;

import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.EnchereComplete;
import fr.eni.ecole.encheres.bo.ObjetEnchere;

public interface EnchereDAO {

	// TODO : methodes liées à EnchereDAOJdbcImpl


	Enchere enchereExistante(int noArticle) throws DALException;

	void InsertEnchere(Enchere derniereEnchere, Connection cnx) throws DALException;

	EnchereComplete lectureEnchereComplete(int noArticle)throws DALException;
	
	
}
