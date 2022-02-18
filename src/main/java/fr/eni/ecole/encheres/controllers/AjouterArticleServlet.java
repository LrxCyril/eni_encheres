package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
import java.time.LocalDate;

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
			
			ajoutArticle.setNom_article(request.getParameter("nom_article"));
			ajoutArticle.setNo_article(Integer.parseInt(request.getParameter("no_article")));
			ajoutArticle.setDescription(request.getParameter("description"));
			// pour convertire une date: LocalDate.of(0, null, 0)
			//ajoutArticle.setDate_debut_encheres( request.getParameter("date_debut_encheres"));
		//récupérer les paramètres de requêtes
			
			String nomArticle = request.getParameter("nom_article");
			String laDescription = request.getParameter("description");
			String laCategorie = request.getParameter("categorie");
			String laPhotoDeArticle = request.getParameter("photo_article");
			String dateDebutEncheres = request.getParameter("date_debut_encheres");
			String dateFinEncheres = request.getParameter("date_fin_encheres");
			String laRue = request.getParameter("rue");
			String leCodePostal = request.getParameter("code_postal");
			String laVille = request.getParameter("ville");
			
			//TODO gérer un objet erreur
			//vérfier si les champs sont vides 
			if (nomArticle.isEmpty()) {
				request.setAttribute("nom_article", "Article");
				
			}
			
			if (laDescription.isEmpty()) {
				request.setAttribute("description", "Description");
				
			}
			
			if (laCategorie.isEmpty()) {
				request.setAttribute("categorie", "Catégorie");
				
			}
			
			if (laPhotoDeArticle.isEmpty()) {
				request.setAttribute("photo_article", "");
				
			}
			
			if (dateDebutEncheres.isEmpty()) {
				request.setAttribute("date_debut_encheres", "");
				
			}
			
			if (dateFinEncheres.isEmpty()) {
				request.setAttribute("date_fin_encheres", "");
				
			}
			
			if (laRue.isEmpty()) {
				request.setAttribute("rue", "Rue");
				
			}
			
			if (leCodePostal.isEmpty()) {
				request.setAttribute("code_postal", "Code postal");
				
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
