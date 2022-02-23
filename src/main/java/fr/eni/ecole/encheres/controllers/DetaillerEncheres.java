package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.EnchereManager;
import fr.eni.ecole.encheres.bll.EnchereRefuseException;
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class DetaillerEncheres
 */
@WebServlet("/detail/enchere")
public class DetaillerEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetaillerEncheres() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/details_encheres.jsp");
		session.setAttribute("idArticle", Integer.parseInt(request.getParameter("idArticle")));
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
		System.out.println(session.getAttribute("utilisateurActif").toString());
		int enchere=Integer.parseInt(request.getParameter("proposition"));
		int idArticle =(int) session.getAttribute("idArticle");
		int idUtilisateur=	utilisateur.getNoUtilisateur();
		int credit=utilisateur.getCredit();
		System.out.println(idUtilisateur);

		
		System.out.println("monid "+idArticle);
		System.out.println(enchere);
		System.out.println(credit);
		
		//noArticle, montant enchere, no encherisseur, credit echerisseur
		try {
			boolean enchereOk=mgrEnchere.traiterEnchere(idArticle, enchere, idUtilisateur, credit);
		session.setAttribute("idArticle", null);
		} catch (EnchereRefuseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
