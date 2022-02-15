package fr.eni.ecole.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.encheres.bll.BLLException;
import fr.eni.ecole.encheres.bll.ConnexionManager;

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
		
		ConnexionManager manager= new ConnexionManager();
		boolean connecte = false;
		try {
			String email = request.getParameter("email");
			System.out.println(email);
		connecte = manager.VerificationUtilisateur(email, request.getParameter("mdp"));
		System.out.println(connecte);
		} catch (BLLException e) {
		
		e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/liste_encheres.jsp");
		if(connecte) {
			rd.forward(request, response);
		}
		
		
		
		
	}

}
