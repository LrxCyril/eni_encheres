package fr.eni.ecole.encheres.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
//Hello
public class Article {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private String rue;
	private String codePostal;
	private String ville;
	
	/**
	 * Constructeur d'article avec numéro d'article et nom d'article
	 * @param noArticle
	 * @param nomArticle
	 */

	
	public Article(int noArticle, String nomArticle) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
	}
	
	/**
	 * Constructeur permettant la création d'un article vide
	 */
	
	public Article() {
		super();
	}

	/**
	 * Numéro d'article getter
	 * @return noArticle int
	 */
	public int getNoArticle() {
		return noArticle;
	}

	
	/**
	 * Numéro d'article setter
	 * @param noArticle int
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * Nom d'article getter
	 * @return nomArticle String
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * Nom d'article setter
	 * @param nomArticle String
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
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
	 * @return dateDebutEncheres Date
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Date début enchères Date setter 
	 * @param localDate Date
	 */
	public void setDateDebutEncheres(LocalDate localDate) {
		this.dateDebutEncheres = localDate;
	}

	/**
	 * Date fin d'enchères Date getter
	 * @return dateFinEncheres Date
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 *Date fin d'enchères Date  setter
	 * @param localDate Date
	 */
	public void setDateFinEncheres(LocalDate localDate) {
		this.dateFinEncheres = localDate;
	}

	/**
	 * Prix initial int getter
	 * @return prixInitial int
	 */
	public int getPrixInitial() {
		return prixInitial;
	}

	/**
	 * Prix initial int setter
	 * @param prixInitial
	 */
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	/**
	 * Prix ventre int getter
	 * @return prixVente int
	 */
	public int getPrixVente() {
		return prixVente;
	}

	
	/**
	 * Prix vente int setter
	 * @param prixVente int
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Numéro utilisateur getter
	 * @return noUtilisateur int
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	
	/**
	 * Numéro utilisateur setter
	 * @param noUtilisateur int
	 */
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	/**
	 * Numéro de catégorie getter
	 * @return noCategorie int
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * Numéro de catégoerie setter
	 * @param noCategorie int
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * Rue pour le retrait getter
	 * @return rue string
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Rue pour le retrait setter
	 * @param rue String
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * CodePostal pour le retrait getter
	 * @return codePostal String
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * CodePostal pour le retrait setter
	 * @param codePostal String
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Ville pour le retrait getter
	 * @return ville String
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Ville pour le retrait setter
	 * @param ville String
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
}