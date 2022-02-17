package fr.eni.ecole.encheres.bo;

import java.util.Date;

public class Article {

	private int no_article;
	private String nom_article;
	private String description;
	private Date date_debut_encheres;
	private Date date_fin_encheres;
	private int prix_initial;
	private int prix_vente;
	private int no_utilisateur;
	private int no_categorie;
	
	/**
	 * Constructeur d'article avec numéro d'article et nom d'article
	 * @param no_article
	 * @param nom_article
	 */

	
	public Article(int no_article, String nom_article) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
	}
	
	/**
	 * Constructeur permettant la création d'un article vide
	 */
	
	public Article() {
		super();
	}

	/**
	 * Numéro d'article getter
	 * @return numéro d'article int
	 */
	public int getNo_article() {
		return no_article;
	}

	
	/**
	 * Numéro d'article setter
	 * @param article no_article int
	 */
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	/**
	 * Nom d'article getter
	 * @return Nom d'article String
	 */
	public String getNom_article() {
		return nom_article;
	}

	/**
	 * Nom d'article setter
	 * @param nom_article String
	 */
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
		
	
		
		
}
