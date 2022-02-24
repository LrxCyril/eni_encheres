 package fr.eni.ecole.encheres.controllers;

import java.io.IOException;
import java.sql.Connection;

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
import fr.eni.ecole.encheres.dal.jdbc.ConnectionProvider;

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

		String modif = (String)request.getAttribute("modif");
		if (modif!=null) {
			request.setAttribute("creer",  (boolean) false);
		}else {		request.setAttribute("creer",  (boolean) true);
		}

		//Appel de la page mon-profil
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
		profilUtilisateur.setMotDePasse(request.getParameter("motDePasse"));
		if(session.getAttribute("utilisateurActif")!=null) {
			profilUtilisateur.setNoUtilisateur(((Utilisateur) session.getAttribute("utilisateurActif")).getNoUtilisateur());
			profilUtilisateur.setCredit(((Utilisateur) session.getAttribute("utilisateurActif")).getCredit());
			profilUtilisateur.setAdministrateur(((Utilisateur) session.getAttribute("utilisateurActif")).isAdministrateur());
			
		}
		//mise à jour de l'utilisateur de session
		session.setAttribute("utilisateurActif",profilUtilisateur);

		String motDePasse = request.getParameter("motDePasse");
		String confirmMotDePasse = request.getParameter("confirmMotDePasse");
		String creation = request.getParameter("creer");
		String modification = request.getParameter("modifier");
		Boolean vide = false;
		//vérfier si les champs sont vides 
		if (profilUtilisateur.getPseudo().isEmpty()) {
			request.setAttribute("pseudo", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getNom().isEmpty()) {
			request.setAttribute("nom", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getPrenom().isEmpty()) {
			request.setAttribute("prenom", "vide");
			vide=true;
		}
		if (!profilUtilisateur.getEmail().contains("@")) {
			request.setAttribute("email", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getEmail().isEmpty()) {
			request.setAttribute("email", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getRue().isEmpty()) {
			request.setAttribute("rue", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getCodePostal().isEmpty()) {
			request.setAttribute("codePostal", "vide");
			vide=true;
		}
		
		if (profilUtilisateur.getVille().isEmpty()) {
			request.setAttribute("ville", "vide");
			vide=true;
		}
	
		
		if (!motDePasse.matches("[a-zA-Z0-9]")){
			System.out.println("invalide");
			request.setAttribute("mauvaisFormat", true);
			vide=true;
		}
		if (motDePasse.isEmpty()) {
			request.setAttribute("motDePasse", "vide");
			vide=true;
		}
 
		if (confirmMotDePasse.isEmpty()) {
			request.setAttribute("confirmMotDePasse", "vide");
			vide=true;
		}
		//comparer motDePasse et confirmMotDePasse
		if (!motDePasse.equals(confirmMotDePasse)) {
			request.setAttribute("motDePasseInvalide", true);
			vide=true;
		}
		//si champ vide ou erreur retourner sur la page, avertir et sortir de la fonction
		if (vide) {
			if (modification!=null ) {
				request.setAttribute("modif","oui");
			}
		doGet(request, response);
		return;
		}
		
		if (creation!=null) {
			try {
				manager.insererUtilisateur((Utilisateur) session.getAttribute("utilisateurActif"));
				//manager.insererUtilisateur(pseudo, nom, prenom,telephone, email, motDePasse, rue, codePostal, ville, 0);
			} catch (BLLException e) {
				//l'utilsateur existe le mentionner et rediriger
				request.setAttribute("userExist", true);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/mon_profil.jsp");
				rd.forward(request, response);
				e.printStackTrace();
				//sortie de la methode
				return;
			}
		}
		if (modification!=null ) {
			try {
				manager.majUtilisateur((Utilisateur) session.getAttribute("utilisateurActif"));
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		//appeler liste des enchères
		RequestDispatcher rd = request.getRequestDispatcher("/home");
		rd.forward(request, response);
	}
}
