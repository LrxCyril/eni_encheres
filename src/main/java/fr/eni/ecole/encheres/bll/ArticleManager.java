package fr.eni.ecole.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;
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

	public void insertArticles(Article article) throws DALException, BLLException {
		//--- Insérer l'article en base
		try {
			articleDAO.insertArticles(article);
		} catch(DALException e){
		//--- Levée d'une exception quand l'article n'est pa inséré
			throw new BLLException("Erreur lors de l'insertion de l'article !");
		}
	}
	
	public List<ArticleVendu> selectArticle() throws BLLException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();;
		LocalDate date=LocalDate.now();
		// --- récupérer la liste des articles en vente
		try {
			articles = articleDAO.selectArticle(date);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'utilisateur n'existe pas !");
		}
		return articles;
		
		
	}
	
	
}
