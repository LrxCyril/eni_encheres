package fr.eni.ecole.encheres.bo;

public class ObjetEnchere {

	int noArticle;
	int credit;
	int miseAPrix;
	int prixVente;
	int noUtilisateur;
	
	
	// --- constructeur
	public ObjetEnchere(int noUtilisateur, int credit, int miseAPrix, int prixVente) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.credit = credit;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		
	}

	// --- Getters and Setters ---------------------
	// --- numéro utilisateur
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	// --- numero article
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	
	// --- credit
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	// --- prix initial
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	
	
	// --- prix vente
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	
}
