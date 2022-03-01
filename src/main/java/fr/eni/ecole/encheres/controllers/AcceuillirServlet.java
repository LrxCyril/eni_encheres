package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.ArticleManager;
import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Categorie;



/**
 * Servlet implementation class AcceuillirServlet
 */
@WebServlet("/home")
public class AcceuillirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager manager =new ArticleManager();
    /**
     * Default constructor. 
     */
    public AcceuillirServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		List<Categorie> categorieArticle =new ArrayList<Categorie>();
		//Categorie cateVide= new Categorie();
		try {
			// recuperation des Articles en BDD
			articles=manager.selectArticle();
			// recuperation des Categories en BDD
			categorieArticle= manager.selectCategorie();
			//creation des attributs pour affichage
			request.setAttribute("listeCategories", categorieArticle);
			request.setAttribute("listeArticles", articles);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean erreur=true;
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		List<Categorie> categorieArticle =new ArrayList<Categorie>();
		int filtreCategorie = 1;
		String recherche = null;
		// recuperation des parametres de filtres
		if ((request.getParameter("categories"))!=null) {
			filtreCategorie=Integer.parseInt(request.getParameter("categories"));
			}
		if ((request.getParameter("rechercheArticle"))!=null) {
			recherche= request.getParameter("rechercheArticle");
			}
		try {
			// recuperation des Articles en BDD
			articles=manager.selectListArticles(filtreCategorie,recherche);	
			// recuperation des Categories en BDD
			categorieArticle= manager.selectCategorie();
			//creation des attributs pour affichage
			request.setAttribute("listeCategories", categorieArticle);
			request.setAttribute("listeArticles", articles);
		} catch (BLLException e) {
				e.printStackTrace();		
		}
		//Appel de la page à afficher
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
		}
}


