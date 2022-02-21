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

import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bo.Article;

/**
 * Servlet implementation class VendreArticleServlet
 */
@WebServlet("/ajout/article")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterArticleServlet() {
        super();
       
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
			
			String nomArticle = request.getParameter("nomArticle");
			String laDescription = request.getParameter("description");
			String laCategorie = request.getParameter("noCategorie");
			String laPhotoDeArticle = request.getParameter("photoArticle");
			String dateDebutEncheres = request.getParameter("dateDebutEncheres");
			String dateFinEncheres = request.getParameter("dateFinEncheres");
			String laRue = request.getParameter("rue");
			String leCodePostal = request.getParameter("codePostal");
			String laVille = request.getParameter("ville");
			
			//TODO gérer un objet erreur
			//vérfier si les champs sont vides 
			if (nomArticle.isEmpty()) {
				request.setAttribute("nomArticle", "Article");
				
			}
			
			if (laDescription.isEmpty()) {
				request.setAttribute("description", "Description");
				
			}
			
			if (laCategorie.isEmpty()) {
				request.setAttribute("noCategorie", "Catégorie");
				
			}
			
			if (laPhotoDeArticle.isEmpty()) {
				request.setAttribute("photoArticle", "");
				
			}
			
			if (dateDebutEncheres.isEmpty()) {
				request.setAttribute("dateDebutEncheres", "");
				
			}
			
			if (dateFinEncheres.isEmpty()) {
				request.setAttribute("dateFinEncheres", "");
				
			}
			
			if (laRue.isEmpty()) {
				request.setAttribute("rue", "Rue");
				
			}
			
			if (leCodePostal.isEmpty()) {
				request.setAttribute("codePostal", "Code postal");
				
			}
	 
			if (laVille.isEmpty()) {
				request.setAttribute("ville", "Ville");
				
			}
			
			doGet(request, response);
			
			//appeler liste des enchères
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
			rd.forward(request, response);
		}

}
