package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class ConnexionManager {

	// --- paramètres transmiss par la servlet
	private String email;
	private String mdp;
	
	// --- association avec la DAL
	private Utilisateur utilisateur;
	
	// --- attribut de navigation vers la DAL
	private UtilisateurDAOJdbcImpl utilisateurDAO;
	
	
	// --- récupérer une instance de la DAL
	public ConnexionManager(){
		super();
		utilisateurDAO = new UtilisateurDAOJdbcImpl();
	}
	
	// --- récupérer les paramètres de SeConnecterServlet
	// --- paramètres "email" et "mdp" (mdp : mot de passe)
	public boolean VerificationUtilisateur(String email, String mdp) throws BLLException{
		this.email = email;
		this.mdp = mdp;
		
		boolean cnx = false;
		
		// --- récupérer le résultat du travail de la DAL
		// --- récupérer un utilisateur
		try {
			utilisateur = (Utilisateur) utilisateurDAO.VerifUtilisateur(email);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'email n'existe pas !");
		}
		
		// --- vérifier si l'utilisateur a un compte
		if (utilisateur.getMotDePasse().contentEquals(mdp)) {
			//cnx est ok
			cnx = true;
		}
		return cnx;	
	}	
}
