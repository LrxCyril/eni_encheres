package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.dal.DALException;

public class VerifVictoire {
	
	
	
	public void verifierMeilleurEnchere(Article article) throws DALException, BLLException {
		//--- Insérer l'article en base
		try {
			articleDAO.insertArticles(article);
		} catch(DALException e){
		//--- Levée d'une exception quand l'article n'est pa inséré
			e.printStackTrace();
			throw new BLLException("Erreur lors de l'insertion de l'article !");
		}
	}

}
