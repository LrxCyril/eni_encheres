package fr.eni.ecole.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	
	int noEnchere;
	LocalDateTime dateEnchere;
	int montantEnchere;
	Article article;
	Utilisateur utilisateur;
	
	
	public Enchere()
{
		super();

	}
	public Enchere( LocalDateTime dateEnchere, int montantEnchere) {
		super();

		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	public Enchere(int noEnchere, LocalDateTime dateEnchere, int montantEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	public int getNoEnchere() {
		return noEnchere;
	}
	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
	
	
}