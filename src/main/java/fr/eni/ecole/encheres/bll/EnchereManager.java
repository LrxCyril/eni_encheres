package fr.eni.ecole.encheres.bll;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.EnchereComplete;
import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.EnchereDAO;
import fr.eni.ecole.encheres.dal.jdbc.ConnectionProvider;

public class EnchereManager {

	EnchereDAO enchereDAO;
	
	public EnchereManager() {
		try {
			enchereDAO = DAOFactory.getEnchereDAO();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public EnchereComplete recupererEnchereEnCours (int noArticle) {
		EnchereComplete enchereComplete = null;
		
		try {
			enchereComplete=enchereDAO.lectureEnchereComplete(noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enchereComplete;
	}
	
	public boolean traiterEnchere(int noArticle, int montantEnchere, int noEncherisseur, int creditEncherisseur)  {
		boolean insertion =true;
		boolean authorisationEnchere = false;
		try {
			// --- 1 | Créer une instance d'enchère
			
			
			ArticleManager mgrArticle = new ArticleManager();
			UtilisateurManager mgrUtilisateur = new UtilisateurManager();
			Enchere derniereEnchere= enchereDAO.enchereExistante(noArticle);
			Enchere	nouvelleEnchere= derniereEnchere;
			//verifier que l'encherisseur peut encherir
				if (creditEncherisseur>montantEnchere) {
					if(derniereEnchere==null) {
						authorisationEnchere=true;
						nouvelleEnchere=new Enchere();
						Article article=new Article();
						article.setNoArticle(noArticle);
						nouvelleEnchere.setArticle(article);
						Utilisateur encherisseur = new Utilisateur();
						encherisseur.setNoUtilisateur(noEncherisseur);
						encherisseur.setCredit(creditEncherisseur-montantEnchere);
						nouvelleEnchere.setUtilisateur(encherisseur);


					}
					if(derniereEnchere!=null) {
					if (montantEnchere>derniereEnchere.getMontantEnchere()) {
						authorisationEnchere=true;
					}}//inserer l'enchère

					if(authorisationEnchere) {
						try {
							Connection cnx = ConnectionProvider.getConnection();
							// Ouvrir une transaction
							cnx.setAutoCommit(false);
								nouvelleEnchere.setDateEnchere(LocalDateTime.now());
								nouvelleEnchere.setMontantEnchere(montantEnchere);
								enchereDAO.InsertEnchere(nouvelleEnchere, cnx);
								if(derniereEnchere!=null) {
								//enregistrer nouvelle valeur de l'article
								derniereEnchere.getArticle().setPrixVente(montantEnchere);
								//Mettre à jour l'article en BDD				
								mgrArticle.MiseAJourArticle(derniereEnchere.getArticle(),cnx);
								//Mettre à jour le credit utilisateur en BDD
								derniereEnchere.getUtilisateur().setCredit(creditEncherisseur);
								}
								//1-nouvel encherisseur	
								Utilisateur encherisseur = new Utilisateur();
								encherisseur.setNoUtilisateur(noEncherisseur);
								encherisseur.setCredit(creditEncherisseur-montantEnchere);
								mgrUtilisateur.MiseAJourCreditUtilisateur(encherisseur,cnx);
								//2-ancien encherisseur si existe
								if (derniereEnchere!=null) {
									int creditAncienEncherisseur=creditEncherisseur+derniereEnchere.getMontantEnchere();
									derniereEnchere.getUtilisateur().setCredit(creditAncienEncherisseur);						
									mgrUtilisateur.MiseAJourCreditUtilisateur(derniereEnchere.getUtilisateur(),cnx);
								}
							//fermer la transaction
							cnx.commit();
						} catch (BLLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					
					}else {
						insertion=false;
						//throw new EnchereRefuseException("Le montant est trop bas");
						}
				}else {
					insertion=false;
					//throw new EnchereRefuseException("votre credit n'est pas suffisant");
					}
	}
		
		catch (DALException e) {
			// TODO Auto-generated catch block
			insertion=false;
			return insertion;	
			//e.printStackTrace();
		}
	return insertion;	
	}
}
