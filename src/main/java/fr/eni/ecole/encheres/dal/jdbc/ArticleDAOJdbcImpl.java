package fr.eni.ecole.encheres.dal.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Categorie;
import fr.eni.ecole.encheres.bo.Retrait;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	// creer constante de requete d'insertion d'un article

	private static final String SQL_SELECT_ARTICLE = "SELECT no_article, nom_article, description, prix_initial,date_fin_encheres, date_debut_encheres, pseudo from ARTICLES_VENDUS  Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur WHERE (date_debut_encheres<=?)AND(date_fin_encheres>=?)";
	private static final String SQL_SELECT_ARTICLE_BY_CATE = "SELECT no_article, nom_article, description, prix_initial,date_fin_encheres, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie=CATEGORIES.no_categorie WHERE ((date_debut_encheres<=?)AND(date_fin_encheres>=?) AND Categories.no_categorie=?)";
	private static final String SQL_SELECT_ARTICLE_BY_NOM = "SELECT no_article, nom_article, description, prix_initial,date_fin_encheres, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur WHERE ((date_debut_encheres<=?)AND(date_fin_encheres>=?)AND nom_article Like ?)";
	private static final String SQL_SELECT_ARTICLE_BY_CATE_NOM = "SELECT no_article, nom_article, description, prix_initial,date_fin_encheres, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie=CATEGORIES.no_categorie WHERE ((date_debut_encheres<=?)AND(date_fin_encheres>=?) AND nom_article Like ? AND Categories.no_categorie=?)";
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SQL_SELECT_LIBELLE = "Select no_categorie, libelle from CATEGORIES";
	private static final String SQL_UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE  no_article=?";
	private static final String SQL_INSERT_ADRESSE_RETRAIT = "INSERT INTO RETRAITS (no_article,rue, code_postal,ville) VALUES (?,?,?,?)";


	@SuppressWarnings("null")
	@Override
	public List<ArticleVendu> selectArticle(LocalDate date) throws DALException {

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); // on s'assure qu'il y a ait toujours une liste,
																		// même vide.

		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
		// 1- Obtenir une connexion
		try (Connection connexion = ConnectionProvider.getConnection();) {
			// 2- Contruire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE);
			// ajout du paramètre à la requete(Where pseudo)
			ordre.setDate(1, java.sql.Date.valueOf(date));
			ordre.setDate(2, java.sql.Date.valueOf(date));
			// Appel de la methode constuisant l'utilisateur
			ResultSet rs = ordre.executeQuery();

			// si il y'a un resultat de requete

			while (rs.next()) {
				ArticleVendu articleLu = new ArticleVendu();
				// Alimentation de l'instance d'utilisateur depuis les champs récupérés de la
				// requette
				articleLu.setNoArticle(rs.getInt("no_article"));
				articleLu.setNomArticle(rs.getString("nom_article"));
				articleLu.setDescription(rs.getString("description"));
				articleLu.setMiseAPrix(rs.getInt("prix_initial"));
				articleLu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				articleLu.setUtilisateur(utilisateur);

				articles.add(articleLu);

			}
			// 5-fermeture de la connexion
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			// Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de lire la connexion");
		}
		return articles;
	}


	@Override
	public List<Categorie> selectLibelle() throws DALException {
		// creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur
		// DAO JdBC Impl
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		// --- Obtenir la requête
		try (Connection connexion = ConnectionProvider.getConnection();) {

			// --- Construire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_LIBELLE);

			// --- Exécuter la requête
			ResultSet rs =ordre.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				listeCategorie.add(categorie);
			}

		} catch (SQLException sqle) {
			// Levée de l'exception pas d'article
			throw new DALException("Insert invalide !");
		}
	
		return listeCategorie;
	}

	@Override
	public List<ArticleVendu> selectArticlebyCateNom(LocalDate date, int filtreCategorie, String recherche) throws DALException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); 
		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
				// 1- Obtenir une connexion
				try (Connection connexion = ConnectionProvider.getConnection();) {
					// 2- Contruire la requete
					PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE_BY_CATE_NOM);
					// ajout du paramètre à la requete(Where pseudo)
					ordre.setDate(1, java.sql.Date.valueOf(date));
					ordre.setDate(2, java.sql.Date.valueOf(date));
					ordre.setString(3,recherche);
					ordre.setInt(4,filtreCategorie);
					// Appel de la methode constuisant l'utilisateur
					ResultSet rs = ordre.executeQuery();

					// si il y'a un resultat de requete

					while (rs.next()) {
						ArticleVendu articleLu = new ArticleVendu();
						// Alimentation de l'instance d'utilisateur depuis les champs récupérés de la
						// requette
						articleLu.setNoArticle(rs.getInt("no_article"));
						articleLu.setNomArticle(rs.getString("nom_article"));
						articleLu.setDescription(rs.getString("description"));
						articleLu.setMiseAPrix(rs.getInt("prix_initial"));
						articleLu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
						Utilisateur utilisateur = new Utilisateur();
						utilisateur.setPseudo(rs.getString("pseudo"));
						articleLu.setUtilisateur(utilisateur);

						articles.add(articleLu);


					}
					// 5-fermeture de la connexion
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					// Levé de l'exception l'utilisateur n'existe pas
					throw new DALException("Impossible de lire la connexion");
				}
				return articles;
	}

	@Override
	public List<ArticleVendu> selectArticlebyCate(LocalDate date, int filtreCategorie) throws DALException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); 
		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
				// 1- Obtenir une connexion
				try (Connection connexion = ConnectionProvider.getConnection();) {
					// 2- Contruire la requete
					PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE_BY_CATE);
					// ajout du paramètre à la requete(Where pseudo)
					ordre.setDate(1, java.sql.Date.valueOf(date));
					ordre.setDate(2, java.sql.Date.valueOf(date));
					ordre.setInt(3,filtreCategorie);
					// Appel de la methode constuisant l'utilisateur
					ResultSet rs = ordre.executeQuery();

					// si il y'a un resultat de requete

					while (rs.next()) {
						ArticleVendu articleLu = new ArticleVendu();
						// Alimentation de l'instance d'utilisateur depuis les champs récupérés de la
						// requette
						articleLu.setNoArticle(rs.getInt("no_article"));
						articleLu.setNomArticle(rs.getString("nom_article"));
						articleLu.setDescription(rs.getString("description"));
						articleLu.setMiseAPrix(rs.getInt("prix_initial"));
						articleLu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
						Utilisateur utilisateur = new Utilisateur();
						utilisateur.setPseudo(rs.getString("pseudo"));
						articleLu.setUtilisateur(utilisateur);

						articles.add(articleLu);
						System.out.println("insert");

					}
					// 5-fermeture de la connexion
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					// Levé de l'exception l'utilisateur n'existe pas
					throw new DALException("Impossible de lire la connexion");
				}
				return articles;
	}

	@Override
	public List<ArticleVendu> selectArticlebyNom(LocalDate date, String recherche) throws DALException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); 
		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
				// 1- Obtenir une connexion
				try (Connection connexion = ConnectionProvider.getConnection();) {
					// 2- Contruire la requete
					PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE_BY_NOM);
					// ajout du paramètre à la requete(Where pseudo)
					ordre.setDate(1, java.sql.Date.valueOf(date));
					ordre.setDate(2, java.sql.Date.valueOf(date));
					ordre.setString(3,recherche);
					// Appel de la methode constuisant l'utilisateur
					ResultSet rs = ordre.executeQuery();

					// si il y'a un resultat de requete

					while (rs.next()) {
						ArticleVendu articleLu = new ArticleVendu();
						// Alimentation de l'instance d'utilisateur depuis les champs récupérés de la
						// requette
						articleLu.setNoArticle(rs.getInt("no_article"));
						articleLu.setNomArticle(rs.getString("nom_article"));
						articleLu.setDescription(rs.getString("description"));
						articleLu.setMiseAPrix(rs.getInt("prix_initial"));
						articleLu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
						Utilisateur utilisateur = new Utilisateur();
						utilisateur.setPseudo(rs.getString("pseudo"));
						articleLu.setUtilisateur(utilisateur);

						articles.add(articleLu);
						System.out.println("insert");

					}
					// 5-fermeture de la connexion
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					// Levé de l'exception l'utilisateur n'existe pas
					throw new DALException("Impossible de lire la connexion");
				}
				return articles;
	}

	@Override
	public void MiseAJourArticle(ArticleVendu article, Connection cnx) throws DALException {
		
		try {
			// 2- Contruire la requete
			PreparedStatement ordre = cnx.prepareStatement(SQL_UPDATE_ARTICLE);
			ordre.setInt(1,article.getPrixVente());
			ordre.setInt(2,article.getNoArticle());
			ordre.executeUpdate();
		}catch  (SQLException sqle){
			//Levé de l'exception l'utilisateur n'existe pas
			throw new DALException("Impossible de mettre à jour la ligne");
		}
	}



	@Override
	public void insertArticleComplet(ArticleVendu ajoutArticle) throws DALException {
		// creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur
				// DAO JdBC Impl
				
				// --- Obtenir la requête
				try (Connection connexion = ConnectionProvider.getConnection();) {

					// --- Construire la requete ajout article
					PreparedStatement ordre = connexion.prepareStatement(SQL_INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
					ordre.setString(1, ajoutArticle.getNomArticle());
					ordre.setString(2, ajoutArticle.getDescription());
					ordre.setDate(3, java.sql.Date.valueOf(ajoutArticle.getDateDebutEncheres()));
					ordre.setDate(4, java.sql.Date.valueOf(ajoutArticle.getDateFinEncheres()));
					ordre.setInt(5, ajoutArticle.getMiseAPrix());
					ordre.setInt(6, ajoutArticle.getUtilisateur().getNoUtilisateur());
					ordre.setInt(7, ajoutArticle.getCategorieArticle().getNoCategorie());
					// --- Exécuter la requête
					int clefAutoGeneree = 0;
					ordre.executeUpdate();
					ResultSet clefs = ordre.getGeneratedKeys();
					// on se place sur la 1ere ligne du resultat...
					if (clefs.next()) {
						// .. et on lilt la valeur de la 1ere colonne (de cette ligne)
						clefAutoGeneree = clefs.getInt(1); // la valeur de la clef est la valeur de la 1ere (et l'unique) colonne du resultat 
					}
					 //construire la requete ajout retrait
					ordre = connexion.prepareStatement(SQL_INSERT_ADRESSE_RETRAIT);
					ordre.setInt(1,clefAutoGeneree);
					ordre.setString(2,ajoutArticle.getLieuRetrait().getRue());
					ordre.setString(3,ajoutArticle.getLieuRetrait().getCodePostal());
					ordre.setString(4,ajoutArticle.getLieuRetrait().getVille());

					ordre.executeUpdate();
				} catch (SQLException sqle) {
					// Levée de l'exception pas d'article
					sqle.printStackTrace();
					throw new DALException("Insert invalide !");
				}
			}
		
}



