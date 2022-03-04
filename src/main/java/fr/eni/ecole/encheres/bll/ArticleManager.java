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
	
	/**
	 * Selection de tout les articles en vente
	 * @return liste d'articles
	 * @throws BLLException
	 */
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
		/**
		 * Selection des categories existantes
		 * @return liste de categorie
		 * @throws BLLException
		 */
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
		public void deleteArticle(int noArticle) throws BLLException {
			try {
				articleDAO.deleteAricle(noArticle);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				e.printStackTrace();
				throw new BLLException("Erreur lors de la suppresion de l'article");
			}
	}
		/**
		 * Selection des articles selon 2 filtres
		 * @param filtreCategorie
		 * @param recherche
		 * @return liste d'articles
		 * @throws BLLException
		 */
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
	/**
	 * selection d'un article selon son no
	 * @param noArticle
	 * @return
	 * @throws BLLException
	 */
		public ArticleVendu selectArticlebyId(int noArticle) throws BLLException {
			ArticleVendu article =new ArticleVendu();
				// --- récupérer la liste des articles en vente
			try {

				article = articleDAO.selectArticlebyId(noArticle);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la selection par nom");
			}
			return article;
		}
		/**
		 * selection des articles selon filtre de categorie
		 * @param filtreCategorie
		 * @return list d'articles
		 * @throws BLLException
		 */
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

		/**
		 * selection des articles selon champs de recherche
		 * @param recherche
		 * @return liste d'articles
		 * @throws BLLException
		 */
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

		/**
		 * Mise à jour d'un aricle existant
		 * @param article
		 * @param cnx
		 * @return connection BDD
		 * @throws BLLException
		 */
		public Connection MiseAJourArticle(ArticleVendu article, Connection cnx) throws BLLException {
			try {
				articleDAO.miseAJourArticle(article,cnx);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la mise à jour de l'article !");
			}
			return cnx;
		}

		/**
		 * Creer un article et l'inserer en BDD
		 * @param noUtilisateur
		 * @param nomArticle
		 * @param description
		 * @param noCategorie
		 * @param dateDebut
		 * @param dateFin
		 * @param prixIntial
		 * @param rue
		 * @param codePostal
		 * @param ville
		 * @throws BLLException
		 */
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

		
		
	
	/**
	 * Verifier les champs de saisi d'un article (regle metier)
	 * et lever une exception en cas de non respect
	 * @param nomArticle
	 * @param description
	 * @param noCategorie
	 * @param dateDebut
	 * @param dateFin
	 * @param prixIntial
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @throws BLLException
	 */
		
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
/**
 * Selectionne les articles pas encore en vente d'un user
 * @param noUtilisateur
 * @return ListArtcle
 * @throws BLLException
 */
	public List<ArticleVendu> selectArticleEnVente(int noUtilisateur) throws BLLException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		LocalDate date=LocalDate.now();
			// --- récupérer la liste des articles en vente
		try {
			articles = articleDAO.selectArticleModif(noUtilisateur,date);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la selection par categorie");
		}
		return articles;
	}
	
	public List<ArticleVendu> selectArticleEnAttente(int noUtilisateur) throws BLLException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		LocalDate date=LocalDate.now();
			// --- récupérer la liste des articles en vente
		try {
			articles = articleDAO.selectArticleEnAttente(noUtilisateur,date);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la selection par categorie");
		}
		return articles;
	}
	public List<ArticleVendu> selectArticleVenteFini(int noUtilisateur) throws BLLException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		LocalDate date=LocalDate.now();
			// --- récupérer la liste des articles en vente
		try {
			articles = articleDAO.selectArticleVenteFini(noUtilisateur,date);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la selection par categorie");
		}
		return articles;
	}

public List<ArticleVendu> selectListArticles(int idUtilisateur,String filtreVente, String filtreAchat) {
	List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
	//enCours //aVenir //fini
	switch (filtreVente) {
		case "enCours":
			try {
				articles=this.selectArticleEnVente(idUtilisateur);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			break;
		case "aVenir":
			try {
				articles=this.selectArticleEnAttente(idUtilisateur);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			break;
			
			
		case "fini":
			try {
				articles=this.selectArticleVenteFini(idUtilisateur);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			break;
	}
	
	return articles;
}
	
	
}
