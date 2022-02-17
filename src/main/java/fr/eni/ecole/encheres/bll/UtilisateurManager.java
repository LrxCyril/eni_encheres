package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	// --- variable d'implementation utilsateur
	private Utilisateur utilisateur;
	// --- variable d'implementation DAL
	private UtilisateurDAO utilisateurDAO;
	
	
	//Constructeur
	public UtilisateurManager(){
		//creation d'une instance d'utilisateur
		utilisateur = new Utilisateur();
		try {
			//creation d'une instance de date
			utilisateurDAO = DAOFactory.getUtilisateurDAO();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public Utilisateur lectureUtilisateur(String pseudo) throws BLLException{
		boolean cnx = false;
		// --- récupérer le résultat du travail de la DAL
		// --- récupérer un utilisateur
		try {
			utilisateur = utilisateurDAO.lireUtilisateurPseudo(pseudo);
			cnx = true;
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'utilisateur n'existe pas !");
		}
		return utilisateur;	
	}
	
	
/**
 * Verification de l'existence d'un utilisateur
 * @param identifiant String
 * @param mdp String
 * @return
 * @throws BLLException
 */
	public Utilisateur verificationUtilisateur(String identifiant, String mdp) throws BLLException{
		boolean cnx = false;
		// --- récupérer un utilisateur
		try {
			utilisateur = utilisateurDAO.VerifUtilisateurIdentifiant(identifiant, mdp);
			cnx = true;
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'utilisateur n'existe pas !");
		}
		return utilisateur;	
	}
	
	
	/**
	 * Verification si email ou pseudo deja present en base
	 * @param pseudo
	 * @param email
	 * @return
	 * @throws BLLException
	 */
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
	public void insererUtilisateur(String pseudo, String nom, String prenom, String telephone, String email, String motDePasse,
		String rue, String codePostal, String ville, int Credit) throws BLLException {
		Utilisateur nouvelUtilisateur = new Utilisateur();
		// construire un utilisateur
		nouvelUtilisateur.setPseudo(pseudo);
		nouvelUtilisateur.setNom(nom);
		nouvelUtilisateur.setPrenom(prenom);
		nouvelUtilisateur.setTelephone(telephone);
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

	public void insererUtilisateur(Utilisateur utilisateur) throws BLLException {
		// construire un utilisateur
		utilisateur.setCredit(utilisateur.getCredit() + 100);
		// inserer l'utilisateur en base
		try {
			utilisateurDAO.InsertUtilisateur(utilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de l'insertion!");
		}
		
	}

	public void majUtilisateur(Utilisateur utilisateurConnecte) throws BLLException {
		try {
			utilisateurDAO.MajUtilisateur(utilisateurConnecte);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de l'insertion!");
		}
		
	}
}
	

