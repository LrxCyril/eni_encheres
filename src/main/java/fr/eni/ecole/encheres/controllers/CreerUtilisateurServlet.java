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
		
			profilUtilisateur=(Utilisateur) session.getAttribute("utilisateurActif");
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
		HttpSession session = request.getSession();
		
		//creation d'un utilisateur avec les données mise à jour
		profilUtilisateur=new Utilisateur();
		profilUtilisateur.setPseudo(request.getParameter("pseudo"));
		profilUtilisateur.setNom(request.getParameter("nom"));
		profilUtilisateur.setPrenom(request.getParameter("prenom"));
		profilUtilisateur.setEmail(request.getParameter("email"));
		profilUtilisateur.setTelephone(request.getParameter("telephone"));
		profilUtilisateur.setVille(request.getParameter("ville"));
		profilUtilisateur.setRue(request.getParameter("rue"));
		profilUtilisateur.setCodePostal(request.getParameter("codePostal"));
		profilUtilisateur.setNo_utilisateur(((Utilisateur) session.getAttribute("utilisateurActif")).getNo_utilisateur());
		profilUtilisateur.setCredit(((Utilisateur) session.getAttribute("utilisateurActif")).getCredit());
		profilUtilisateur.setAdministrateur(((Utilisateur) session.getAttribute("utilisateurActif")).isAdministrateur());
		profilUtilisateur.setMotDePasse(((Utilisateur) session.getAttribute("utilisateurActif")).getMotDePasse());
		//mise à jour de l'utilisateur de session
		session.setAttribute("utilisateurActif",profilUtilisateur);

		String motDePasse = request.getParameter("motDePasse");
		String confirmMotDePasse = request.getParameter("confirmMotDePasse");
		String creation = request.getParameter("creer");
		String modification = request.getParameter("modifier");

		//vérfier si les champs sont vides 
		if (profilUtilisateur.getPseudo().isEmpty()) {
			request.setAttribute("pseudo", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getNom().isEmpty()) {
			request.setAttribute("nom", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getPrenom().isEmpty()) {
			request.setAttribute("prenom", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getEmail().isEmpty()) {
			request.setAttribute("email", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getRue().isEmpty()) {
			request.setAttribute("rue", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getCodePostal().isEmpty()) {
			request.setAttribute("codePostal", "vide");
			doGet(request, response);
		}
		
		if (profilUtilisateur.getVille().isEmpty()) {
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
				manager.insererUtilisateur((Utilisateur) session.getAttribute("utilisateurActif"));
				//manager.insererUtilisateur(pseudo, nom, prenom,telephone, email, motDePasse, rue, codePostal, ville, 0);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		if (modification!=null) {
			try {
				manager.majUtilisateur((Utilisateur) session.getAttribute("utilisateurActif"));
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		//appeler liste des enchères
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		rd.forward(request, response);
	}
}
