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
		Categorie cateVide= new Categorie();
		try {
			articles=manager.selectArticle();
			categorieArticle= manager.selectCategorie();
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
		boolean erreur=true;
		List<ArticleVendu> articles =new ArrayList<ArticleVendu>();
		List<Categorie> categorieArticle =new ArrayList<Categorie>();
		int filtreCategorie = 1;
		String recherche = null;
		try {
			if ((request.getParameter("categories"))!=null) {
				filtreCategorie=Integer.parseInt(request.getParameter("categories"));
				System.out.println(filtreCategorie);
				}

			recherche= request.getParameter("rechercheArticle");
			//filtres
			if (filtreCategorie !=1) {
				//filtre par recherche et categorie
				if(recherche!=null) {
					articles=manager.selectArticlebyCateNom(filtreCategorie,recherche);				
				// filtre par Categorie
				}else {
					
					articles=manager.selectArticlebyCate(filtreCategorie);
				}}
			//filtre par recherche	
			if(recherche!=null&&filtreCategorie ==1) {
				articles=manager.selectArticlebyNom(recherche);
			}
			erreur=false;
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NumberFormatException nf) {
			nf.printStackTrace();
		}finally {
			
		try {	
			if(erreur |(recherche==null&&filtreCategorie ==1) ) {
				articles=manager.selectArticle();	
			}

			categorieArticle= manager.selectCategorie();
			Categorie cateVide= new Categorie();
			request.setAttribute("listeCategories", categorieArticle);
			request.setAttribute("listeArticles", articles);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);}
	}

}
