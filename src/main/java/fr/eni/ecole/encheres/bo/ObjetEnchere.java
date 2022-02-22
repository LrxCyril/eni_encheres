package fr.eni.ecole.encheres.bo;

public class ObjetEnchere {

	int noArticle;
	int credit;
	int miseAPrix;
	int prixVente;
	
	
	// --- constructeur
	public ObjetEnchere(int noArticle, int credit, int miseAPrix, int prixVente) {
		super();
		this.noArticle = noArticle;
		this.credit = credit;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}

	// --- Getters and Setters ---------------------
	// --- numero utilisateur
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
	public int getPrixInitial() {
		return miseAPrix;
	}
	public void setPrixInitial(int miseAPrix) {
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
