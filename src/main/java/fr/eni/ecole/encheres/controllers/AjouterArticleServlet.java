package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.encheres.bll.ArticleManager;
import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.dal.DALException;

/**
 * Servlet implementation class VendreArticleServlet
 */
@WebServlet("/ajout/article")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleManager mgr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterArticleServlet() {
        super();
        mgr = new ArticleManager();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajout_article.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Création d'un article avec les données mise à jour
		
			Article ajoutArticle = new Article();
			Boolean vide = false;
			
			ajoutArticle.setNomArticle(request.getParameter("nomArticle"));
			ajoutArticle.setNoArticle(Integer.parseInt(request.getParameter("noArticle")));
			ajoutArticle.setDescription(request.getParameter("description"));
			ajoutArticle.setNoCategorie(Integer.parseInt(request.getParameter("noCategorie")));
			//Photo article
			//TODO
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ajoutArticle.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEncheres"),dtf));
			ajoutArticle.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEncheres"),dtf));
			ajoutArticle.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			ajoutArticle.setPrixVente(Integer.parseInt(request.getParameter("prixVente")));
			
		//récupérer les paramètres de requêtes
			
			//envoyer article fonctionBLL
			try {
				mgr.insertArticles(ajoutArticle);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			//TODO gérer un objet erreur
			//vérfier si les champs sont vides 
			if (ajoutArticle.getNomArticle().isEmpty()) {
				request.setAttribute("nomArticle", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getDescription().isEmpty()) {
				request.setAttribute("description", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getNoCategorie().isEmpty()) {
				request.setAttribute("noCategorie", "vide");
				vide = true;
			}
			
			
			if (ajoutArticle.getDateDebutEncheres().isEmpty()) {
				request.setAttribute("dateDebutEncheres", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getDateFinEncheres().isEmpty()) {
				request.setAttribute("dateFinEncheres", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getRue().isEmpty()) {
				request.setAttribute("rue", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getCodePostal.isEmpty()) {
				request.setAttribute("codePostal", "vide");
				vide = true;
			}
	 
			if (ajoutArticle.getVille.isEmpty()) {
				request.setAttribute("ville", "vide");
				vide = true;
			}
			
			doGet(request, response);
			
			//appeler liste des enchères
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
			rd.forward(request, response);
		}

}
