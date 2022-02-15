package fr.eni.ecole.encheres.bo;

public class Utilisateur {
	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	
	
	/**
	 * Constructeur d'utilisateur avec identifant et mot de passe utilisateur
	 * @param email
	 * @param motDePasse
	 */
	public Utilisateur(String email, String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur permettant le creation d'un utilisateur vide
	 */
	public Utilisateur() {
		super();
	}

	/**
	 * Numero d'utilisateur getter
	 * @return numero utilisateur int
	 */
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	/**
	 * Numero d'utilisateur setter
	 * param utilisateur int
	 */
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	/**
	 * Pseudo getter
	 * @return Pseudo String
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * pseudo setter
	 * @param pseudo String
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Nom getter
	 * @return nom String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Nom Setter
	 * @param nom String
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * prenom getter
	 * @return prenom String
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter prenom
	 * @param prenom String
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * email getter
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * email setter
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 *tel getter String
	 * @return telephone String
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * tel Setter String
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * rue getter
	 * @return rue String
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * rue setter 
	 * @param rue String
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * code postal getter
	 * @return code postal String
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * code postal setter
	 * @param codePostal String
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * ville getter
	 * @return ville String
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * setter ville
	 * @param ville String
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * getter mot de passe
	 * @return mot de passe String
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * setter mot de passe
	 * @param motDePasse String
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * getter string
	 * @return credit string
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * setter credit
	 * @param credit string
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}

	/**
	 * getter admin
	 * @return bool administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}

	/**
	 * setter admin
	 * @param administrateur bool
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	
	
	
	
}
