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
 * Servlet implementation class CreerUtilisateurServlet
 */
@WebServlet("/creer/utilisateur")
public class CreerUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager manager;  
    private Utilisateur profilUtilisateur;
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

		//Alimentation de la page mon_profil en mode modification
		String modif = (String)request.getParameter("modif");
	
		if(modif!=null) {
			HttpSession session = request.getSession();
			String loginConnecte=(String) session.getAttribute("login");
			try {
//todo 
//fonctionner avec l'instance utilisateur de session pour ne pas refaire la requete en base
				//Lecture des informations utilisateurs en base
				profilUtilisateur=manager.lectureUtilisateur(loginConnecte);
				System.out.println(profilUtilisateur.toString());
			} catch (BLLException e) {
				request.setAttribute("erreurIhm", true);
				e.printStackTrace();
			}
			//Alimentation des attributs de la page profil
			request.setAttribute("pseudoLu", profilUtilisateur.getPseudo());
			request.setAttribute("nomLu",  profilUtilisateur.getNom());
			request.setAttribute("prenomLu",  profilUtilisateur.getPrenom());
			request.setAttribute("emailLu",  profilUtilisateur.getEmail());
			request.setAttribute("telLu",  profilUtilisateur.getTelephone());
			request.setAttribute("rueLu",  profilUtilisateur.getRue());
			request.setAttribute("cPLu",  profilUtilisateur.getCodePostal());
			request.setAttribute("villeLu",  profilUtilisateur.getVille());
			request.setAttribute("mdpLu",  profilUtilisateur.getMotDePasse());
			//modification du bouton creer profil en modifier profil
			request.setAttribute("creer",  (boolean) false);
		}else {
			//modification du bouton modifier profil en creer profil
			request.setAttribute("creer",  (boolean) true);
		}

		//Appel de la page profil
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
		String creation = request.getParameter("creer");
		String modification = request.getParameter("modifier");
		
		System.out.println(creation);
		System.out.println(modification);

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
		
		if (creation!=null) {
			try {
				manager.insererUtilisateur(pseudo, nom, prenom, email, motDePasse, rue, codePostal, ville, 0);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		if (modification!=null) {
			try {
				manager.majUtilisateur(pseudo, nom, prenom, email, motDePasse, rue, codePostal, ville, 0);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		//appeler liste des enchères
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}
}
