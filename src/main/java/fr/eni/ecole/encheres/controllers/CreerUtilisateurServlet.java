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
 * Servlet implementation class CreerUtilisateurServlet
 */
@WebServlet("/creer/utilisateur")
public class CreerUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager manager;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerUtilisateurServlet() {
        manager = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mon_profil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//récupérer les paramètres de requêtes
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String confirmMotDePasse = request.getParameter("confirmMotDePasse");
		
		//TODO gérer un objet erreur
		//vérfier si les champs sont vides 
		if (pseudo.isEmpty()) {
			request.setAttribute("pseudo", "vide");
			doGet(request, response);
		}
		
		if (nom.isEmpty()) {
			request.setAttribute("nom", "vide");
			doGet(request, response);
		}
		
		if (prenom.isEmpty()) {
			request.setAttribute("prenom", "vide");
			doGet(request, response);
		}
		
		if (email.isEmpty()) {
			request.setAttribute("email", "vide");
			doGet(request, response);
		}
		
		if (rue.isEmpty()) {
			request.setAttribute("rue", "vide");
			doGet(request, response);
		}
		
		if (codePostal.isEmpty()) {
			request.setAttribute("codePostal", "vide");
			doGet(request, response);
		}
		
		if (ville.isEmpty()) {
			request.setAttribute("ville", "vide");
			doGet(request, response);
		}
		
		if (motDePasse.isEmpty()) {
			request.setAttribute("motDePasse", "vide");
			doGet(request, response);
		}
 
		if (confirmMotDePasse.isEmpty()) {
			request.setAttribute("confirmMotDePasse", "vide");
			doGet(request, response);
		}
		
		//comparer motDePasse et confirmMotDePasse
		if (!motDePasse.equals(confirmMotDePasse)) {
			request.setAttribute("motDePasseInvalide", "invalide");
			doGet(request, response);
		}
		
		try {
			manager.insererUtilisateur(pseudo, nom, prenom, email, motDePasse, rue, codePostal, ville, 0);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		//appeler liste des enchères
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}
}
