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
/**
 * Recherche de la liste d'article en fonction des filtres fournies
 * @param filtreCategorie
 * @param recherche
 * @return Liste d'articles
 * @throws BLLException
 */
		public List<ArticleVendu> selectListArticles(int filtreCategorie, String recherche) throws BLLException {
			List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
			boolean erreur=true;	
			try {
				//lecture des filtres
				if (filtreCategorie !=1) {
					if(recherche!=null) {
						//filtre par recherche et categorie
						articles= selectArticlebyCateNom(filtreCategorie,recherche);				
					}else {
							// filtre par Categorie Uniquement
							articles= selectArticlebyCate(filtreCategorie);
						}
					}
				if(recherche!=null&&filtreCategorie ==1) {
					//filtre par recherche Uniquement
					articles= selectArticlebyNom(recherche);}
				erreur=false;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BLLException();
			}finally {
				//Aucun filtre
				if(erreur |(recherche==null&&filtreCategorie ==1) ) {
					articles=selectArticle();}	
			}
			return articles;
		}
public void insertVerifSaisi(String nomArticle, String description, int noCategorie, LocalDate dateDebut,
		LocalDate dateFin, int prixIntial, String rue, String codePostal, String ville) throws BLLException {
	List<String> listErreur = new ArrayList();
	int idTabl =0;

	//verifier les erreurs dans le champs de saisi
		if (nomArticle.isEmpty()) {
			listErreur.add("le nom article est vide");
		}
		if (description.isEmpty()) {
			listErreur.add("le description est vide");
		}
		
		if (dateDebut.isAfter(dateFin)) {
			listErreur.add("la date de début d'enchère ne peut pas être posterieure à sa date de fin");
		}
		
		if (rue.isEmpty()) {
			listErreur.add("la rue est vide");
		}
		if (codePostal.isEmpty()) {
			listErreur.add("le code postal est vide");
		}
		if (ville.isEmpty()) {
			listErreur.add("la ville est vide");
		}
		
		if (!listErreur.isEmpty()) {
			throw new BLLException(listErreur.toString());
		}
}
}
