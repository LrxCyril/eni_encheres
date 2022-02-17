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
@WebServlet("/modifier/utilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur profilUtilisateur;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateurServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		profilUtilisateur=(Utilisateur) session.getAttribute("utilisateurActif");
		//Alimentation des attributs de la page profil
		session.setAttribute("pseudoLu", profilUtilisateur.getPseudo());
		session.setAttribute("nomLu",  profilUtilisateur.getNom());
		session.setAttribute("prenomLu",  profilUtilisateur.getPrenom());
		session.setAttribute("emailLu",  profilUtilisateur.getEmail());
		session.setAttribute("telLu",  profilUtilisateur.getTelephone());
		session.setAttribute("rueLu",  profilUtilisateur.getRue());
		session.setAttribute("cPLu",  profilUtilisateur.getCodePostal());
		session.setAttribute("villeLu",  profilUtilisateur.getVille());
		session.setAttribute("mdpLu",  profilUtilisateur.getMotDePasse());
		//modification du bouton creer profil en modifier profil
		session.setAttribute("creer",  (boolean) false);
		request.setAttribute("modif","oui");
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
