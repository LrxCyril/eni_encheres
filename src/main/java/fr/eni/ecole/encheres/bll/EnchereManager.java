package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.jdbc.EnchereDAO;

public class EnchereManager {

	EnchereDAO enchereDAO;
	
	public EnchereManager() {
	}
	
	public void traiterEnchere(int noArticle, int montantEnchere, int noUtilisateur, int creditAcheteur) {
	
		try {
			// --- 1 | Créer une instance d'enchère
			enchereDAO = DAOFactory.getEnchereDAO();
			ObjetEnchere premiereEnchere = null;
			
			// --- 2 | Créer une instance de l'objet
	
			premiereEnchere = enchereDAO.premiereEnchere(noArticle);
			
			int idUtilisateurVendeur = premiereEnchere.getNoUtilisateur();
			int miseAPrix = premiereEnchere.getMiseAPrix();
			int prixArticle = premiereEnchere.getPrixVente();
			int creditVendeur = premiereEnchere.getCredit();
			int nouveauCreditAcheteur = creditAcheteur - montantEnchere;
			int nouveauCreditVendeur = creditVendeur + montantEnchere;
			
			//éléments de test
			System.out.println(idUtilisateurVendeur);
			System.out.println(miseAPrix);
			System.out.println(prixArticle);
			System.out.println(creditVendeur);
			
			// --- 3 | Les vérifications à faire
			// - vérifier la validité de la première enchère
			if (montantEnchere > miseAPrix) {
				// - Si le crédit de l'acheteur est supérieur au montant de l'enchère
				if (creditAcheteur > montantEnchere) {
					// - mettre à jour le prix de vente
					enchereDAO.nouveauPrixVente(noArticle, montantEnchere);
					
					// - mettre à jour le crédit de l'acheteur
					enchereDAO.debiterAcheteur(noUtilisateur, nouveauCreditAcheteur);
					
					// - mettre à jour le crédit du vendeur
					enchereDAO.crediterAcheteur(noUtilisateur, nouveauCreditVendeur);
					
					// - mettre à jour la meilleure offre pour l'objet
					enchereDAO.meilleureOffre(noUtilisateur, montantEnchere);
					
				}					
			}
		
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
