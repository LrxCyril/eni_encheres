package fr.eni.ecole.encheres.bo;

public class Retrait {
	
	String rue;
	String codePostal;
	String ville;
	
	
	/**
	 * Constructeur d'adresse avec rue, code postal et ville
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	/**
	 * Constructeur permettant la création d'une adresse de retrait vide
	 */
	
	public Retrait() {
		super();
	}
	// --- Getters and Setters ----------------------
	
	// --- rue
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	// --- code postal
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String code_postal) {
		this.codePostal = code_postal;
	}
	
	// --- ville
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
