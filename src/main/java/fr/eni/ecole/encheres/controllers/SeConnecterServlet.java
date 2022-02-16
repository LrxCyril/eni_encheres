package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class SeConnecterServlet
 */
@WebServlet("/connect")
public class SeConnecterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SeConnecterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/connexion.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		UtilisateurManager manager= new UtilisateurManager();
		boolean connecte = false;
		request.setAttribute("La_connexion", connecte);
		// --- Si l'identifant OU le mot de passe est " vide " alors la connexion refusée et redirection vers la page de connexion
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		if(identifiant.isBlank() || mdp.isBlank()) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/connexion.jsp");
			rd.forward(request, response);
		}
		//verification de l'existance de l'utilisateur
		try {
			connecte = manager.verificationUtilisateur(identifiant, mdp);
		
		} catch (BLLException e) {
		// --- Si la connexion échoue, redirige vers la page de connexion
			if(!connecte ) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion/connexion.jsp");
				rd.forward(request, response);
				}
		}
		// --- Si la connexion est validée, redirige vers la page d'accueil liste des enchères
		if(connecte) {
			request.setAttribute("La_connexion", connecte);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
			rd.forward(request, response);
		}
	}
}
