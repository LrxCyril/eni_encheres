package fr.eni.ecole.encheres.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			utilisateur = utilisateurDAO.verifUtilisateurIdentifiant(identifiant, mdp);
			System.out.println("BLL"+utilisateur.getNoUtilisateur());
			cnx = true;
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("L'utilisateur n'existe pas !");
		}
		return utilisateur;	
	}
	
	
	public void insererUtilisateur(Utilisateur utilisateur) throws BLLException {

		
	}

	public void majUtilisateur(Utilisateur utilisateurConnecte) throws BLLException {
		try {
			utilisateurDAO.majUtilisateur(utilisateurConnecte);
			
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la mise a jour de l'utilisateur!");
		}
		
		
		
	}

	public void supprimerUtilisateur(int noUtilisateur) throws BLLException {
		try {
			utilisateurDAO.supprimerUtilisateur(noUtilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de la suppression de l'utilisateur!");
		}
		
	}


		public void miseAJourCreditUtilisateur(Utilisateur encherisseur, Connection cnx) throws BLLException {
			try {
				utilisateurDAO.miseAJourCreditUtilisateur(encherisseur,cnx);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de la mise à jour credit de l'utilisateur !");
			}
	
		}

		public void verifSaisi(String pseudo, String nom, String prenom, String telephone, String email, String rue, String codePostal, String ville, String motDePasse,
				String confirmMotDePasse) throws BLLException {
		List<String> listErreur = new ArrayList();
		int idTabl =0;

		//verifier les erreurs dans le champs de saisi
			if (pseudo.isEmpty()) {
				listErreur.add("le pseudo est vide");
			}
			if (nom.isEmpty()) {
				listErreur.add("le nom est vide");
			}
			if (prenom.isEmpty()) {
				listErreur.add("le prenom est vide");
			}
			if (!email.contains("@")|email.isEmpty()) {
				listErreur.add("l'email est vide");
			}
			if (rue.isEmpty()) {
				listErreur.add("la rue est vide");
			}
			if (codePostal.isEmpty()) {
				listErreur.add("le code postal est vide");
			}
			if (ville.isEmpty()) {
				listErreur.add("la ville est vide");
			}
			if (!motDePasse.matches("[a-zA-Z0-9]")){
				listErreur.add("le mot de passe n'est pas authorisé");
			}
			if (motDePasse.isEmpty()) {
				listErreur.add("le mot de passe est vide");
			}
			if (confirmMotDePasse.isEmpty()) {
				listErreur.add("la confirmation de mot de passe est vide");
			}
			//comparer motDePasse et confirmMotDePasse
			if (!motDePasse.equals(confirmMotDePasse)) {
				listErreur.add("les mots de passes ne correspondent pas");
			}
			if (!listErreur.isEmpty()) {
				throw new BLLException(listErreur.toString());
			}
		}

		public Utilisateur insererUtilisateur(int noUtilisteur, String pseudo, String nom, String prenom, String telephone,
				String email, String motDePasse, String rue, String codePostal, String ville, int credit,
				boolean administrateur) throws BLLException {
			// construire un utilisateur
			utilisateur.setNoUtilisateur(noUtilisteur);
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setTelephone(telephone);
			utilisateur.setEmail(email);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setRue(rue);
			utilisateur.setCredit(utilisateur.getCredit() + 100);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setAdministrateur(administrateur);
			// inserer l'utilisateur en base
			try {
				utilisateurDAO.insertUtilisateur(utilisateur);
			} catch (DALException e) {
				// --- Levée d'une exception quand l'email n'est pas reconnu
				throw new BLLException("Erreur lors de l'insertion utilisateur existant!");
			}
			
			return utilisateur;
		}
		
}
	

