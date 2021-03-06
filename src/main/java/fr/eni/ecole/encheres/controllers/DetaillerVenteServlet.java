package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.ArticleManager;
import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bll.EnchereManager;
import fr.eni.ecole.encheres.bll.EnchereRefuseException;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.EnchereComplete;
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class DetaillerEncheres
 */
@WebServlet("/detail/vente")
public class DetaillerVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetaillerVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArticleManager mgrArticle =new ArticleManager();
		ArticleVendu article;
		session.setAttribute("idArticle", Integer.parseInt(request.getParameter("idArticle")));
		try {
			article= mgrArticle.selectArticlebyId(Integer.parseInt(request.getParameter("idArticle")));
			request.setAttribute("article", article);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modif_article.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		EnchereManager mgrEnchere =new EnchereManager();
		Utilisateur utilisateur;
		utilisateur= (Utilisateur) session.getAttribute("utilisateurActif");
		int enchere=Integer.parseInt(request.getParameter("proposition"));
		int idArticle =(int) session.getAttribute("idArticle");
		int idUtilisateur=	utilisateur.getNoUtilisateur();
		int credit=utilisateur.getCredit();
	
		//noArticle, montant enchere, no encherisseur, credit echerisseur
	
		boolean enchereOk=mgrEnchere.traiterEnchere(idArticle, enchere, idUtilisateur, credit);

		if(!enchereOk) {
			request.setAttribute("enchereInvalide",true);
			request.setAttribute("encherevalide",false);
		}else {
			request.setAttribute("encherevalide",true);
			request.setAttribute("enchereInvalide",false);
		}
		//session.setAttribute("idArticle", null);
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/details_encheres.jsp");
		//rd.forward(request, response);
		doGet(request, response);
	}

}