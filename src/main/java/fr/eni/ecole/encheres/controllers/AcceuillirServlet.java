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

import fr.eni.ecole.encheres.bll.ArticleManager;
import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bo.ArticleVendu;



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
		List<String> categorieArticle =new ArrayList<String>();
		try {
			articles=manager.selectArticle();
			categorieArticle= manager.selectCategorie();
			categorieArticle.add(0, "");
			request.setAttribute("listeCategories", categorieArticle);
			request.setAttribute("listeArticles", articles);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		List<String> categorieArticle =new ArrayList<String>();
		String filtreCategorie=request.getParameter("categories");
		String recherche= request.getParameter("rechercheArticle");
		System.out.println(filtreCategorie);
		System.out.println(recherche);
		try {
			//filtres
			if (!filtreCategorie.isEmpty()) {
				//filtre par recherche et categorie
				if(!recherche.isBlank()) {
					articles=manager.selectArticlebyCateNom(filtreCategorie,recherche);				
				// filtre par Categorie
				}else {
					articles=manager.selectArticlebyCate(filtreCategorie);
				}}
			//filtre par recherche
			if(!recherche.isBlank()&&filtreCategorie.isEmpty()) {
				articles=manager.selectArticlebyNom(recherche);
			}
			
			categorieArticle= manager.selectCategorie();
			categorieArticle.add(0, "");
			request.setAttribute("listeCategories", categorieArticle);
			request.setAttribute("listeArticles", articles);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}

}
