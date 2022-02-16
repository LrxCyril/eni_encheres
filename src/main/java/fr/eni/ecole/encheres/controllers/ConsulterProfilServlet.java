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
import fr.eni.ecole.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConsulterProfilServlet
 */
@WebServlet("/consulter/profil")
public class ConsulterProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UtilisateurManager manager;
     private Utilisateur profilUtilisateur;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterProfilServlet() {
    	manager = new UtilisateurManager();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo="slo";
		try {
			profilUtilisateur=manager.lectureUtilisateur(pseudo);
		} catch (BLLException e) {
			request.setAttribute("erreurIhm", true);
			e.printStackTrace();
		}
		//Alimentation des attributs de la page profil
		request.setAttribute("pseudo", profilUtilisateur.getPseudo());
		request.setAttribute("nom",  profilUtilisateur.getNom());
		request.setAttribute("prenom",  profilUtilisateur.getPrenom());
		request.setAttribute("email",  profilUtilisateur.getEmail());
		request.setAttribute("telephone",  profilUtilisateur.getTelephone());
		request.setAttribute("rue",  profilUtilisateur.getRue());
		request.setAttribute("codepostal",  profilUtilisateur.getCodePostal());
		request.setAttribute("ville",  profilUtilisateur.getVille());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/affichage_profil.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
