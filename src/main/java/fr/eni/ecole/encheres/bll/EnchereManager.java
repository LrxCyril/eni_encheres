package fr.eni.ecole.encheres.bll;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fr.eni.ecole.encheres.bo.ArticleVendu;
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
	
	public List<ArticleVendu> selectMesOffres (int noUtilisateur) throws BLLException {
		List<ArticleVendu>mesOffres = null;
		Utilisateur moi= new Utilisateur();
		moi.setNoUtilisateur(noUtilisateur);
		try {
			mesOffres=enchereDAO.selectMesOffres(moi);
			for (ArticleVendu offre : mesOffres) {
				// la vente n'est pas terminee
				if( LocalDate.now().isBefore(offre.getDateFinEncheres())) {
					if(offre.getMonOffre()==offre.getPrixVente()) {
						offre.setEtatVenteTxt("vous êtes le meilleur encherisseur");
						offre.setEtatVente(0);
					}else {	
						offre.setEtatVenteTxt("vous n'êtes pas le meilleur encherisseur");			
						offre.setEtatVente(0);
					}
				// la vente est terminee
				}else {
					if(offre.getMonOffre()==offre.getPrixVente()) {
						offre.setEtatVenteTxt("vous avez remporté la vente");
						offre.setEtatVente(10);
					}else {	
						offre.setEtatVenteTxt("vous avez perdu la vente");
						offre.setEtatVente(11);
					}
				}
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();		
		}
		
		return mesOffres;
		
	}
	
	public EnchereComplete recupererEnchereEnCours (int noArticle) throws BLLException {
		EnchereComplete enchereComplete = null;
		
		try {
			enchereComplete=enchereDAO.lectureEnchereComplete(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
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
						ArticleVendu article=new ArticleVendu();
						article.setNoArticle(noArticle);
						nouvelleEnchere.setArticle(article);
						
					}
					Utilisateur encherisseur = new Utilisateur();
					encherisseur.setNoUtilisateur(noEncherisseur);
					encherisseur.setCredit(creditEncherisseur-montantEnchere);
					nouvelleEnchere.setUtilisateur(encherisseur);
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
								enchereDAO.insertEnchere(nouvelleEnchere, cnx);
								if(derniereEnchere!=null) {
								//enregistrer nouvelle valeur de l'article
								derniereEnchere.getArticle().setPrixVente(montantEnchere);
								//Mettre à jour l'article en BDD				
								mgrArticle.MiseAJourArticle(derniereEnchere.getArticle(),cnx);
								//Mettre à jour le credit utilisateur en BDD
								derniereEnchere.getUtilisateur().setCredit(creditEncherisseur);
								}
							//1-nouvel encherisseur	
								encherisseur.setNoUtilisateur(noEncherisseur);
								encherisseur.setCredit(creditEncherisseur-montantEnchere);
								mgrUtilisateur.miseAJourCreditUtilisateur(encherisseur,cnx);
							//2-ancien encherisseur si existe
								if (derniereEnchere!=null) {
									int creditAncienEncherisseur=creditEncherisseur+derniereEnchere.getMontantEnchere();
									derniereEnchere.getUtilisateur().setCredit(creditAncienEncherisseur);						
									mgrUtilisateur.miseAJourCreditUtilisateur(derniereEnchere.getUtilisateur(),cnx);
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
