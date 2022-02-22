package fr.eni.ecole.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	
	LocalDateTime dateEnchere;
	int montantEnchere;
	ArticleVendu articleVendu;
	
	// --- Getters and Setters
	// --- date enchère
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	// --- montant enchère
	public int getMontantEnchere() {
		return montantEnchere;
	}
	
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	// --- article vendu
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	
	
}
