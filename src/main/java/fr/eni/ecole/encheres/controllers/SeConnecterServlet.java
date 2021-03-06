package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bll.UtilisateurManager;
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class SeConnecterServlet
 */
@WebServlet("/connect")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utilisateur utilisateurConnecte;
    /**
     * Default constructor. 
     */
    public SeConnecterServlet() {
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/connexion.jsp");
		rd.forward(request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation de la session en cours
		HttpSession session = request.getSession();
		UtilisateurManager manager= new UtilisateurManager();
		boolean connecte = false;
		request.setAttribute("La_connexion", connecte);
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		//verification de l'existance de l'utilisateur
		try {
			utilisateurConnecte = manager.verificationUtilisateur(identifiant, mdp);
			System.out.println("servlet"+utilisateurConnecte.getNoUtilisateur());
		} catch (BLLException e) {
		// --- Si la connexion échoue, l'utilisateur n'a pas été trouve
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/connexion.jsp");
				rd.forward(request, response);
				return;
		}
			//Alimentation des attributs de la page profil
		  	session.setAttribute("utilisateurActif", utilisateurConnecte);
			session.setAttribute("session_active", true);
			session.setAttribute("profilRecherche",identifiant);
			session.setAttribute("login",identifiant);
			request.setAttribute("La_connexion", connecte);
			// --- Si la connexion est validée, redirige vers la page d'accueil liste des enchères
			RequestDispatcher rd = request.getRequestDispatcher("/home");
			rd.forward(request, response);

	}
}

