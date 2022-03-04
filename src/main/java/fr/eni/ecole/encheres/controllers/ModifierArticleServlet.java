package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ModifierUtilisateurServlet
 */
@WebServlet("/modifier/article")
public class ModifierArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur profilUtilisateur;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierArticleServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation de la session active
		HttpSession session = request.getSession();
		profilUtilisateur=(Utilisateur) session.getAttribute("utilisateurActif");
		int noArticle=Integer.parseInt(request.getParameter("article"));
		//appel de la page
		RequestDispatcher rd = request.getRequestDispatcher("/creer/utilisateur");
		rd.forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
