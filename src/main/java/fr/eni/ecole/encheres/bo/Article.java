package fr.eni.ecole.encheres.bo;

import java.time.LocalDate;
import java.util.Date;

public class Article {

	private int no_article;
	private String nom_article;
	private String description;
	private LocalDate date_debut_encheres;
	private LocalDate date_fin_encheres;
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
	 * @return nno_article int
	 */
	public int getNo_article() {
		return no_article;
	}

	
	/**
	 * Numéro d'article setter
	 * @param no_article int
	 */
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	/**
	 * Nom d'article getter
	 * @return nom_article String
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

	/**
	 * Description getter
	 * @return description String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Description setter
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * Date début d'enchères Date getter
	 * @return date_debut_encheres Date
	 */
	public LocalDate getDate_debut_encheres() {
		return date_debut_encheres;
	}

	/**
	 * Date début enchères Date setter 
	 * @param localDate Date
	 */
	public void setDate_debut_encheres(LocalDate localDate) {
		this.date_debut_encheres = localDate;
	}

	/**
	 * Date fin d'enchères Date getter
	 * @return date_fin_encheres Date
	 */
	public LocalDate getDate_fin_encheres() {
		return date_fin_encheres;
	}

	/**
	 *Date fin d'enchères Date  setter
	 * @param date_fin_encheres Date
	 */
	public void setDate_fin_encheres(LocalDate date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	/**
	 * Prix initial int getter
	 * @return prix_initial int
	 */
	public int getPrix_initial() {
		return prix_initial;
	}

	/**
	 * Prix initial int setter
	 * @param prix_initial
	 */
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	/**
	 * Prix ventre int getter
	 * @return prix_vente int
	 */
	public int getPrix_vente() {
		return prix_vente;
	}

	
	/**
	 * Prix vente int setter
	 * @param prix_vente int
	 */
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	/**
	 * Numéro utilisateur getter
	 * @return no_tulisateur int
	 */
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	
	/**
	 * Numéro utilisateur setter
	 * @param no_utilisateur int
	 */
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	/**
	 * Numéro de catégorie getter
	 * @return no_categorie int
	 */
	public int getNo_categorie() {
		return no_categorie;
	}

	/**
	 * Numéro de catégoerie setter
	 * @param no_categorie int
	 */
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	
}
