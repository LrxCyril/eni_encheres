package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;
import fr.eni.ecole.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class ConnexionManager {

	// --- association avec la DAL
	private Utilisateur utilisateur;
	
	// --- attribut de navigation vers la DAL
	private UtilisateurDAO utilisateurDAO;
	
	
	// --- récupérer une instance de la DAL
	public ConnexionManager(){
		super();
		try {
			utilisateurDAO = DAOFactory.getUtilisateurDAO();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// --- récupérer les paramètres de SeConnecterServlet
	// --- paramètres "email" et "mdp" (mdp : mot de passe)
	public boolean VerificationUtilisateur(String identifiant, String mdp) throws BLLException{
		boolean cnx = false;
		
		// --- récupérer le résultat du travail de la DAL
		// --- récupérer un utilisateur
		try {
			System.out.println(identifiant);
			if (identifiant.contains("@")) {
				//appel du pseudo
			utilisateur = (Utilisateur) utilisateurDAO.VerifUtilisateurEmail(identifiant);
			}else {
				//appel de l'email
			utilisateur = (Utilisateur) utilisateurDAO.VerifUtilisateurPseudo(identifiant);
			}
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
