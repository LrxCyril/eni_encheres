package fr.eni.ecole.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Categorie;
import fr.eni.ecole.encheres.bo.Enchere;
import fr.eni.ecole.encheres.bo.EnchereComplete;
import fr.eni.ecole.encheres.bo.ObjetEnchere;
import fr.eni.ecole.encheres.bo.Retrait;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.DALException;
import fr.eni.ecole.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	// --- 1 | Créer les requêtes pour les objets enchères

	private static final String SQL_SELECT_ENCHERE = "SELECT UTILISATEURS.no_utilisateur, credit, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur WHERE no_article = ? ";
	private static final String SQL_SELECT_ENCHERE_EXIST = "SELECT TOP 1 no_enchere, date_enchere, montant_enchere,ENCHERES.no_article, ENCHERES.no_utilisateur, credit, ARTICLES_VENDUS.prix_initial, ARTICLES_VENDUS.prix_vente FROM ENCHERES inner Join UTILISATEURS on ENCHERES.no_utilisateur =UTILISATEURS.no_utilisateur inner Join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article WHERE ENCHERES.no_article=? ORDER BY montant_enchere DESC;";
	private static final String SQL_INSERT_ENCHERE = "INSERT INTO ENCHERES (date_enchere,montant_enchere,no_article,no_utilisateur) values (?,?,?,?)"; // première enchère
	private static final String SQL_ENCHERE_COMPLETE_OLD="SELECT TOP 1  UTILISATEURS.no_utilisateur as [no_encherisseur],UTILISATEURS.pseudo as [encherisseur],no_enchere,nom_article, description, date_fin_encheres, montant_enchere,prix_initial, ENCHERES.no_article ,(SELECT pseudo from ARTICLES_VENDUS inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where no_article=?) as [vendeur],(SELECT telephone from ARTICLES_VENDUS inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where no_article=?)as [tel_vendeur],(SELECT Utilisateurs.no_utilisateur from ARTICLES_VENDUS inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where no_article=?)as[no_vendeur],RETRAITS.code_postal,RETRAITS.rue,RETRAITS.ville FROM ENCHERES inner Join UTILISATEURS on ENCHERES.no_utilisateur =UTILISATEURS.no_utilisateur inner Join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article inner Join RETRAITS on ARTICLES_VENDUS.no_article=RETRAITS.no_article WHERE ENCHERES.no_article=? ORDER BY montant_enchere DESC;";
	private static final String SQL_UPDATE_ENCHERE = null; // surenchérir
	private static final String SQL_ENCHERE_COMPLETE="SELECT (Select TOP 1 ENCHERES.no_enchere  FROM ENCHERES  where ENCHERES.no_article=? ORDER BY ENCHERES.montant_enchere DESC)as[no_enchere],(Select TOP 1 ENCHERES.montant_enchere  FROM ENCHERES  where ENCHERES.no_article=? ORDER BY ENCHERES.montant_enchere DESC)as[montant_enchere],(Select TOP 1 UTILISATEURS.no_utilisateur  FROM ENCHERES inner join UTILISATEURS on ENCHERES.no_utilisateur=UTILISATEURS.no_utilisateur  where ENCHERES.no_article=? ORDER BY ENCHERES.montant_enchere DESC)as[no_encherisseur],(Select TOP 1 UTILISATEURS.pseudo  FROM ENCHERES inner join UTILISATEURS on ENCHERES.no_utilisateur=UTILISATEURS.no_utilisateur  where ENCHERES.no_article=? ORDER BY ENCHERES.montant_enchere DESC)as[encherisseur],CATEGORIES.libelle,UTILISATEURS.no_utilisateur as [no_vendeur],UTILISATEURS.pseudo as [vendeur],telephone as [tel_vendeur],nom_article,ARTICLES_VENDUS.no_article, description, date_fin_encheres,prix_initial,RETRAITS.code_postal,RETRAITS.rue,RETRAITS.ville FROM ARTICLES_VENDUS inner Join CATEGORIES on CATEGORIES.no_categorie=ARTICLES_VENDUS.no_categorie Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur =UTILISATEURS.no_utilisateur  inner Join RETRAITS on ARTICLES_VENDUS.no_article=RETRAITS.no_article WHERE ARTICLES_VENDUS.no_article=?;";
	private static final String SQL_MES_ENCHERES="SELECT max (montant_enchere)as [mon_offre],ENCHERES.no_article,date_fin_encheres,prix_vente as[offre_max],ARTICLES_VENDUS.nom_article from encheres inner join ARTICLES_VENDUS on ENCHERES.no_article=ARTICLES_VENDUS.no_article where ENCHERES.no_utilisateur=? Group by ENCHERES.no_article,date_fin_encheres,prix_vente,ARTICLES_VENDUS.nom_article;";


	@Override

	public EnchereComplete lectureEnchereComplete(int noArticle) throws DALException {

		EnchereComplete enchereComplete = new EnchereComplete();
		// --- 1 | Obtenir une connexion
		try (Connection cnx = ConnectionProvider.getConnection();){

		// --- 2 | Construire la requête
			PreparedStatement ordre = cnx.prepareStatement(SQL_ENCHERE_COMPLETE);

		// --- 3 | Ajouter le paramètre à la requête (Where...)
		
			ordre.setInt(1, noArticle);
			ordre.setInt(2, noArticle);
			ordre.setInt(3, noArticle);
			ordre.setInt(4, noArticle);
			ordre.setInt(5, noArticle);

		// --- 4 | Appeler la méthode construisant l'enchère
			ResultSet rs = ordre.executeQuery(); 
		// --- 5 | Vérifier si la connexion est existante
			if (rs.next()) {
				Utilisateur encherisseur = new Utilisateur();
				encherisseur.setNoUtilisateur(rs.getInt("no_encherisseur"));
				encherisseur.setPseudo(rs.getString("encherisseur"));
				Utilisateur vendeur = new Utilisateur();
				vendeur.setNoUtilisateur(rs.getInt("no_vendeur"));
				vendeur.setPseudo(rs.getString("vendeur"));
				vendeur.setTelephone(rs.getString("tel_vendeur"));
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				Retrait retrait = new Retrait();
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setRue(rs.getString("rue"));
				retrait.setVille(rs.getString("ville"));
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(noArticle);
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				Categorie categorie = new Categorie();
				categorie.setLibelle(rs.getString("libelle"));
				enchereComplete.setCategorie(categorie);
				enchereComplete.setEncherisseur(encherisseur);
				enchereComplete.setEnchere(enchere);
				enchereComplete.setRetrait(retrait);
				enchereComplete.setArticle(article);
				enchereComplete.setVendeur(vendeur);							
			}

		} catch (SQLException e) {
			// --- 6 | Lever l'exception : le numéro utilisateur n'existe pas
			e.printStackTrace();
			throw new DALException("le numéro article n'existe pas");
		}
		return enchereComplete;
	}


	@Override
	public Enchere enchereExistante(int noArticle) throws DALException {
		Enchere encherePrecedente = null;
		// --- 1 | Obtenir une connexion
		try (Connection cnx = ConnectionProvider.getConnection();){
		// --- 2 | Construire la requête
			PreparedStatement ordre = cnx.prepareStatement(SQL_SELECT_ENCHERE_EXIST);
		// --- 3 | Ajouter le paramètre à la requête (Where...)
			ordre.setInt(1, noArticle);
		// --- 4 | Appeler la méthode construisant l'enchère
			ResultSet rs = ordre.executeQuery(); 
		// --- 5 | Vérifier si la connexion est existante
			if (rs.next()) {
				//construction des objets a retourner
				Utilisateur encherisseur = new Utilisateur();
				encherisseur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				encherisseur.setCredit(rs.getInt("credit"));
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setMiseAPrix((rs.getInt("prix_initial")));
				article.setPrixVente(rs.getInt("prix_vente"));	
				encherePrecedente = new Enchere(noArticle,LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()),rs.getTime("date_enchere").toLocalTime()), rs.getInt("montant_enchere"));
				encherePrecedente.setArticle(article);
				encherePrecedente.setUtilisateur(encherisseur);
			}
		} catch (SQLException e) {
			// --- 6 | Lever l'exception : le numéro utilisateur n'existe pas
			e.printStackTrace();
			throw new DALException("le numéro article n'existe pas");
		}
		return encherePrecedente;
	}


	@Override
	public  void InsertEnchere(Enchere derniereEnchere, Connection cnx)throws DALException {

		try{
			PreparedStatement ordre = cnx.prepareStatement(SQL_INSERT_ENCHERE);
			ordre.setTimestamp(1,Timestamp.valueOf(derniereEnchere.getDateEnchere()));
			ordre.setInt(2,derniereEnchere.getMontantEnchere());
			ordre.setInt(3,derniereEnchere.getArticle().getNoArticle());
			ordre.setInt(4,derniereEnchere.getUtilisateur().getNoUtilisateur());

			ordre.executeUpdate();
			//--- 5- Fermer la connexion
			} catch (SQLException sqle){
				sqle.printStackTrace();
				//Levé de l'exception pas de Nom
				throw new DALException("Insert invalide !");
			}


	}
	@Override
	public List<ArticleVendu> selectMesOffres(Utilisateur moi) throws DALException {

		List<ArticleVendu> mesEncheres = new ArrayList<ArticleVendu>(); // on s'assure qu'il y a ait toujours une liste,
																		// même vide.

		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		// 1- Obtenir une connexion
		try (Connection connexion = ConnectionProvider.getConnection();) {
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_MES_ENCHERES);
			// ajout du paramètre à la requete(Where pseudo)
			ordre.setInt(1, moi.getNoUtilisateur());
			// Appel de la methode constuisant l'utilisateur
			ResultSet rs = ordre.executeQuery();

			// si il y'a un resultat de requete

			while (rs.next()) {
				ArticleVendu articleLu = new ArticleVendu();
				// Alimentation de l'instance d'utilisateur depuis les champs récupérés de la
				// requette
				articleLu.setNoArticle(rs.getInt("no_article"));
				articleLu.setNomArticle(rs.getString("nom_article"));
				articleLu.setMonOffre(rs.getInt("mon_offre"));
				articleLu.setPrixVente(rs.getInt("offre_max"));
				articleLu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleLu.setUtilisateur(moi);

				mesEncheres.add(articleLu);

			}
			// 5-fermeture de la connexion
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			// Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return mesEncheres;
	}





}