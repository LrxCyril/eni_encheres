package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.bo.Categorie;
import fr.eni.ecole.encheres.dal.DALException;

/**
 * Servlet implementation class VendreArticleServlet
 */
@WebServlet("/ajout/article")
public class AjouterArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager manager =new ArticleManager();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterArticleServlet() {
        super();
        //mgr = new ArticleManager();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> categorieArticle =new ArrayList<Categorie>();
		try {
			categorieArticle= manager.selectCategorie();		
			request.setAttribute("listeCategories", categorieArticle);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			HttpSession session = request.getSession();
			ajoutArticle.setNoUtilisateur(((Utilisateur) session.getAttribute("utilisateurActif")).getNoUtilisateur());
			ajoutArticle.setNomArticle(request.getParameter("nom_article"));
			ajoutArticle.setDescription(request.getParameter("description"));
			ajoutArticle.setNoCategorie(Integer.parseInt(request.getParameter("categories")));
			//Photo article
			//TODO
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ajoutArticle.setDateDebutEncheres(LocalDate.parse(request.getParameter("date_debut_encheres"),dtf));
			ajoutArticle.setDateFinEncheres(LocalDate.parse(request.getParameter("date_fin_encheres"),dtf));
			ajoutArticle.setPrixInitial(Integer.parseInt(request.getParameter("prix_initial")));
			
		//récupérer les paramètres de requêtes
			
			//envoyer article fonctionBLL
			try {
				manager.insertArticles(ajoutArticle);
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
				request.setAttribute("nom_article", "vide");
				vide = true;
			}
			
			if (ajoutArticle.getDescription().isEmpty()) {
				request.setAttribute("description", "vide");
				vide = true;
			}
			
		//	if (ajoutArticle.getNoCategorie().isEmpty()) {
		//		request.setAttribute("no_categorie", "vide");
		//		vide = true;
		//	}
			
			
		//	if (ajoutArticle.getDateDebutEncheres().isEmpty()) {
		//		request.setAttribute("date_debut_encheres", "vide");
		//		vide = true;
		//	}
			
		//	if (ajoutArticle.getDateFinEncheres().isEmpty()) {
		//		request.setAttribute("date_fin_encheres", "vide");
		//		vide = true;
		//	}
			
		//	if (ajoutArticle.getRue().isEmpty()) {
		//		request.setAttribute("rue", "vide");
		//		vide = true;
		//	}
			
		//	if (ajoutArticle.getCodePostal.isEmpty()) {
		//		request.setAttribute("code_postal", "vide");
		//		vide = true;
		//	}
	 
		//	if (ajoutArticle.getVille.isEmpty()) {
		//		request.setAttribute("ville", "vide");
		//		vide = true;
		//	}
			
		
			
			//appeler liste des enchères
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
			rd.forward(request, response);
		}

}
