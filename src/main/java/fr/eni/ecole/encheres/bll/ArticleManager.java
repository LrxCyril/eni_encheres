package fr.eni.ecole.encheres.bll;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Categorie;
import fr.eni.ecole.encheres.bo.Retrait;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;	
	
	
	public ArticleManager() {
		try 
		{
			//creation d'une instance de date
			articleDAO = DAOFactory.getArticleDAO();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}


	
	public List<ArticleVendu> selectArticle() throws BLLException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		LocalDate date=LocalDate.now();
			// --- récupérer la liste des articles en vente
		try {
			articles = articleDAO.selectArticle(date);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la selection des articles");
		}
		return articles;
	}
		
		public List<Categorie> selectCategorie() throws BLLException {
			List<Categorie> categorieArticle =new ArrayList<Categorie>();
				// --- récupérer la liste des articles en vente
			try {
				categorieArticle = articleDAO.selectLibelle();
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la selection des categories");
			}
			return categorieArticle;
		
	}

		public List<ArticleVendu> selectArticlebyCateNom(int filtreCategorie, String recherche) throws BLLException {
			List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
			LocalDate date=LocalDate.now();
				// --- récupérer la liste des articles en vente
			try {
				String articleRecherche = "%"+recherche+"%";
				articles = articleDAO.selectArticlebyNom(date, articleRecherche);
				articles = articleDAO.selectArticlebyCateNom(date, filtreCategorie, articleRecherche);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la selection par nom");
			}
			return articles;
		}

		public List<ArticleVendu> selectArticlebyCate(int filtreCategorie) throws BLLException {
			List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
			LocalDate date=LocalDate.now();
				// --- récupérer la liste des articles en vente
			try {
				articles = articleDAO.selectArticlebyCate(date, filtreCategorie);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la selection par categorie");
			}
			return articles;
		}
		

		public List<ArticleVendu> selectArticlebyNom(String recherche) throws BLLException {
			List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
			LocalDate date=LocalDate.now();
				// --- récupérer la liste des articles en vente
			try {
				String articleRecherche = "%"+recherche+"%";
				articles = articleDAO.selectArticlebyNom(date, articleRecherche);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la selection des articles");
			}
			return articles;
		}

		public Connection MiseAJourArticle(ArticleVendu article, Connection cnx) throws BLLException {
			try {
				articleDAO.miseAJourArticle(article,cnx);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la mise à jour de l'article !");
			}
			return cnx;
		}

		public void insertArticlesRetrait(int noUtilisateur, String nomArticle, String description, int noCategorie,
				LocalDate dateDebut, LocalDate dateFin, int prixIntial, String rue, String codePostal, String ville) throws BLLException {
			
			ArticleVendu ajoutArticle = new ArticleVendu();
			Boolean vide = false;
			Retrait ajoutRetrait = new Retrait();
			Utilisateur utilisateur = new Utilisateur();
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(noCategorie);
			utilisateur.setNoUtilisateur(noUtilisateur);
			ajoutRetrait.setCodePostal(codePostal);
			ajoutRetrait.setRue(rue);
			ajoutRetrait.setVille(ville);
			ajoutArticle.setNomArticle(nomArticle);
			ajoutArticle.setDescription(description);
			ajoutArticle.setDateDebutEncheres(dateDebut);
			ajoutArticle.setDateFinEncheres(dateFin);
			ajoutArticle.setMiseAPrix(prixIntial);
			ajoutArticle.setUtilisateur(utilisateur);
			ajoutArticle.setLieuRetrait(ajoutRetrait);
			ajoutArticle.setCategorieArticle(categorie);
			
			
			try {
				//Insérer article
				articleDAO.insertArticleComplet(ajoutArticle);
				//articleDAO.insertRetrait(ajoutRetrait);
				
			} catch(DALException e){
			//--- Levée d'une exception quand l'article n'est pa inséré
				e.printStackTrace();
				throw new BLLException("Erreur lors de l'insertion de l'article !");
			}
			
			
			
		}
	
	
	
	
}
