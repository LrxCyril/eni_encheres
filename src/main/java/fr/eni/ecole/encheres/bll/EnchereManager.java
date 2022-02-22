package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.jdbc.EnchereDAO;

public class EnchereManager {

	EnchereDAO enchereDAO;
	
	public EnchereManager() {
	}
	
	public void traiterEnchere(int noArticle, int montantEnchere, int noUtilisateur) {
	
		try {
			// --- 1 | Créer une instance d'enchère
			enchereDAO = DAOFactory.getEnchereDAO();
			ObjetEnchere premiereEnchere = null;
			
			// --- 2 | Créer une instance de l'objet
	
			premiereEnchere = enchereDAO.premiereEnchere(noArticle);
			//éléments de test
			System.out.println(premiereEnchere.getNoArticle());
			System.out.println(premiereEnchere.getPrixInitial());
			System.out.println(premiereEnchere.getPrixVente());
			System.out.println(premiereEnchere.getCredit());
			
			// --- 3 | Les vérifications à faire
			if (premiereEnchere.getPrixInitial() > montantEnchere) {
				
			}
		
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
