package fr.eni.ecole.encheres.bll;

import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.InsererUtilisateurDAO;


public class InsererUtilisateurManager {
private static InsererUtilisateurDAO Insert;
	
public InsererUtilisateurManager() {
		try {
			Insert = (InsererUtilisateurDAO) DAOFactory.getInsererUtilisateurDAO();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Verification si le mail et le pseudo existe en base
	 * @param pseudo String
	 * @param email String
	 * @return boolean true ou false
	 */
	public boolean ExistanceIdentifiant(String pseudo, String email) {
		//verifier si l'identifiant ou pseudo existe en base
		boolean exist=false;
		try {	
			Insert.VerifIdentifiantExistant(email,pseudo);
			exist=true;
		}catch(DALException e) {
			//il n'existe pas, on fait rien, tout est ok.
		}
		return exist;
	}
	
	/**
	 * Construire un utilisateur et l'inserer en base
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
	public void InsererUtilisateur(String pseudo, String nom, String prenom, String email,String motDePasse, String rue, String codePostal,String ville,int Credit) throws BLLException{
		Utilisateur nouvelUtilisateur= new Utilisateur();
		//construire un utilisateur
		nouvelUtilisateur.setPseudo(pseudo);
		nouvelUtilisateur.setNom(nom);
		nouvelUtilisateur.setPrenom(prenom);
		nouvelUtilisateur.setEmail(email);
		nouvelUtilisateur.setMotDePasse(motDePasse);
		nouvelUtilisateur.setRue(rue);
		nouvelUtilisateur.setCodePostal(codePostal);
		nouvelUtilisateur.setVille(ville);
		nouvelUtilisateur.setCredit(Credit+100);
		//inserer l'utilisateur en  base
		try {
			Insert.InsertUtilisateur(nouvelUtilisateur);
		} catch (DALException e) {
			// --- Levée d'une exception quand l'email n'est pas reconnu
			throw new BLLException("Erreur lors de l'insertion!");
		}
	}
}

