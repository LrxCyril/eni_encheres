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
			//configuration de la page mon profil en mode modification
			request.setAttribute("creer",  (boolean) false);
		}else {	
			//configuration de la page mon profil en mode creation
			request.setAttribute("creer",  (boolean) true);
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
		String pseudo=request.getParameter("pseudo");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String email =request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String ville=request.getParameter("ville");
		String rue =request.getParameter("rue");
		String codePostal=request.getParameter("codePostal");
		String motDePasse=request.getParameter("motDePasse");
		String confirmMotDePasse = request.getParameter("confirmMotDePasse");
		String creation = request.getParameter("creer");
		String modification = request.getParameter("modifier");
		int noUtilisteur = 0;
		int credit = 0;
		boolean administrateur = false;		
		if(session.getAttribute("utilisateurActif")!=null) {
			noUtilisteur= ((Utilisateur) session.getAttribute("utilisateurActif")).getNoUtilisateur();
			credit=((Utilisateur) session.getAttribute("utilisateurActif")).getCredit();
			administrateur=((Utilisateur) session.getAttribute("utilisateurActif")).isAdministrateur();
		}
		String[][] tablErreur;

		if (creation!=null) {
			try {
				//si champ vide ou erreur retourner sur la page, avertir et sortir de la fonction
				try {
				manager.verifSaisi(pseudo, nom, prenom,telephone, email, rue, codePostal, ville, motDePasse,confirmMotDePasse);
				}catch (BLLException e) {
						request.setAttribute("erreur", e.getMessage());
					if (modification!=null ) {
						request.setAttribute("modif","oui");
					}
					doGet(request, response);
					return;
				}
				profilUtilisateur=manager.insererUtilisateur(noUtilisteur,pseudo, nom, prenom,telephone, email, motDePasse, rue, codePostal, ville, credit,administrateur);
				//mise à jour de l'utilisateur de session
				session.setAttribute("utilisateurActif",profilUtilisateur);
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
