package fr.eni.ecole.encheres.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	/*
	 * public boolean existanceIdentifiant(String pseudo, String email) throws BLLException {
	 */
	/*
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
*/

	public void insererUtilisateur(Utilisateur utilisateur) throws BLLException {
		// construire un utilisateur
		utilisateur.setCredit(utilisateur.getCredit() + 100);
		// inserer l'utilisateur en base
		try {
			utilisateurDAO.InsertUtilisateur(utilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de l'insertion utilisateur existant!");
		}
		
	}

	public void majUtilisateur(Utilisateur utilisateurConnecte) throws BLLException {
		try {
			utilisateurDAO.MajUtilisateur(utilisateurConnecte);
			
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la mise a jour de l'utilisateur!");
		}
		
		
		
	}

	public void supprimerUtilisateur(int noUtilisateur) throws BLLException {
		try {
			utilisateurDAO.SupprimerUtilisateur(noUtilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la suppression de l'utilisateur!");
		}
		
	}


		public void MiseAJourCreditUtilisateur(Utilisateur encherisseur, Connection cnx) throws BLLException {
			try {
				utilisateurDAO.MiseAJourCreditUtilisateur(encherisseur,cnx);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la mise à jour credit de l'utilisateur !");
			}
	
		}
}
	

