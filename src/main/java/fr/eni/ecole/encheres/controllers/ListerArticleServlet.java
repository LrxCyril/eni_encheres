package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
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
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ListerArticleServlet
 */
@WebServlet(name = "afficher/article", urlPatterns = { "/afficher/article" })
public class ListerArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager manager =new ArticleManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ArticleVendu> mesArticlesEnVente; 
		HttpSession session = request.getSession();
		int noUtilisateur=((Utilisateur) session.getAttribute("utilisateurActif")).getNoUtilisateur();
		try {
			mesArticlesEnVente =manager.selectArticleEnVente(noUtilisateur);
			request.setAttribute("mesOffres", mesArticlesEnVente);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mes_offres.jsp");
		rd.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
