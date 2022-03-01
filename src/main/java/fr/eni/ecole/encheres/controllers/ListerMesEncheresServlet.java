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

import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bll.EnchereManager;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ListerMesEncheresServlet
 */
@WebServlet("/consulter/offres")
public class ListerMesEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EnchereManager mgrEnchere= new EnchereManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerMesEncheresServlet() {
        
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ArticleVendu> mesOffres; 
		HttpSession session = request.getSession();
		int noUtilisateur=((Utilisateur) session.getAttribute("utilisateurActif")).getNoUtilisateur();
		try {
			mesOffres =mgrEnchere.selectMesOffres(noUtilisateur);
			request.setAttribute("mesOffres", mesOffres);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mes_offres.jsp");
		rd.forward(request, response);}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
