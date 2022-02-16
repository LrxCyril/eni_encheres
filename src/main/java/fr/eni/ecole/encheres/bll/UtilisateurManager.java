package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	// --- association avec la DAL
	private Utilisateur utilisateur;
	
	// --- attribut de navigation vers la DAL
	private UtilisateurDAO utilisateurDAO;
	
	
	// --- récupérer une instance de la DAL
	public UtilisateurManager(){
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
	public boolean verificationUtilisateur(String identifiant, String mdp) throws BLLException{
		boolean cnx = false;
		
		// --- récupérer le résultat du travail de la DAL
		// --- récupérer un utilisateur
		try {
			utilisateur = (Utilisateur) utilisateurDAO.VerifUtilisateurIdentifiant(identifiant, mdp);
			cnx = true;
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'utilisateur n'existe pas !");
		}
		return cnx;	
	}
	public boolean existanceIdentifiant(String pseudo, String email) throws BLLException {
		// verifier si l'identifiant ou pseudo existe en base
		boolean exist = false;
		try {
			String nom;
			nom = utilisateurDAO.VerifIdentifiantExistant(email, pseudo);
			if (nom.isEmpty()) {
				exist = true;
			}
		} catch (DALException e) {
			throw new BLLException("Lecture de valeur impossible");
		}
		return exist;
	}

	/**
	 * Construire un utilisateur et l'inserer en base
	 * 
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param Credit
	 * @throws BLLException
	 */
	public void insererUtilisateur(String pseudo, String nom, String prenom, String email, String motDePasse,
			String rue, String codePostal, String ville, int Credit) throws BLLException {
		Utilisateur nouvelUtilisateur = new Utilisateur();
		// construire un utilisateur
		nouvelUtilisateur.setPseudo(pseudo);
		nouvelUtilisateur.setNom(nom);
		nouvelUtilisateur.setPrenom(prenom);
		nouvelUtilisateur.setEmail(email);
		nouvelUtilisateur.setMotDePasse(motDePasse);
		nouvelUtilisateur.setRue(rue);
		nouvelUtilisateur.setCodePostal(codePostal);
		nouvelUtilisateur.setVille(ville);
		nouvelUtilisateur.setCredit(Credit + 100);
		// inserer l'utilisateur en base
		try {
			utilisateurDAO.InsertUtilisateur(nouvelUtilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de l'insertion!");
		}
	}
}
	

