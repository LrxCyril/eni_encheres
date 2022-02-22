package fr.eni.ecole.encheres.dal.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fr.eni.ecole.encheres.bo.Article;
import fr.eni.ecole.encheres.bo.ArticleVendu;
import fr.eni.ecole.encheres.bo.Utilisateur;
import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	// creer constante de requete d'insertion d'un article

	private static final String SQL_SELECT_ARTICLE = "SELECT no_article, nom_article, description, prix_initial, date_debut_encheres, pseudo from ARTICLES_VENDUS  Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur WHERE (date_debut_encheres>?)";
	private static final String SQL_SELECT_ARTICLE_BY_CATE = "SELECT no_article, nom_article, description, prix_initial, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie=CATEGORIES.no_categorie WHERE (date_debut_encheres>? AND libelle=?)";
	private static final String SQL_SELECT_ARTICLE_BY_NOM = "SELECT no_article, nom_article, description, prix_initial, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur WHERE (date_debut_encheres>? AND nom_article=?)";
	private static final String SQL_SELECT_ARTICLE_BY_CATE_NOM = "SELECT no_article, nom_article, description, prix_initial, date_debut_encheres, pseudo from ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie=CATEGORIES.no_categorie WHERE (date_debut_encheres>? AND nom_article=? AND libelle=?)";
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SQL_SELECT_LIBELLE = "Select libelle from CATEGORIES";
	
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
				articleLu.setDateFinEncheres(rs.getDate("date_debut_encheres").toLocalDate());
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
	public void insertArticles(Article nouvelArticle) throws DALException {
		// creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur
		// DAO JdBC Impl
		
		// --- Obtenir la requête
		try (Connection connexion = ConnectionProvider.getConnection();) {

			// --- Construire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_INSERT_ARTICLE);
			ordre.setInt(1, nouvelArticle.getNoArticle());
			ordre.setString(2, nouvelArticle.getNomArticle());
			ordre.setString(3, nouvelArticle.getDescription());
			ordre.setDate(4, java.sql.Date.valueOf(nouvelArticle.getDateDebutEncheres()));
			ordre.setDate(5, java.sql.Date.valueOf(nouvelArticle.getDateFinEncheres()));
			ordre.setInt(6, nouvelArticle.getPrixInitial());
			ordre.setInt(8, nouvelArticle.getNoUtilisateur());
			ordre.setInt(9, nouvelArticle.getNoCategorie());

			// --- Exécuter la requête
			ordre.executeUpdate();

		} catch (SQLException sqle) {
			// Levée de l'exception pas d'article
			throw new DALException("Insert invalide !");
		}
	}

	@Override
	public List<String> selectLibelle() throws DALException {
		// creer commande SQL inserer article (idem inserer utilisateur dans Utilisateur
		// DAO JdBC Impl
		List<String> listeCategorie = new ArrayList<String>();
		// --- Obtenir la requête
		try (Connection connexion = ConnectionProvider.getConnection();) {

			// --- Construire la requete
			PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_LIBELLE);

			// --- Exécuter la requête
			ResultSet rs =ordre.executeQuery();
			while (rs.next()) {
				listeCategorie.add((rs.getString("libelle")));
			}

		} catch (SQLException sqle) {
			// Levée de l'exception pas d'article
			throw new DALException("Insert invalide !");
		}
	
		return listeCategorie;
	}

	@Override
	public List<ArticleVendu> selectArticlebyCateNom(LocalDate date, String filtreCategorie, String recherche) throws DALException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); 
		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
				// 1- Obtenir une connexion
				try (Connection connexion = ConnectionProvider.getConnection();) {
					// 2- Contruire la requete
					PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE_BY_CATE_NOM);
					// ajout du paramètre à la requete(Where pseudo)
					ordre.setDate(1, java.sql.Date.valueOf(date));

					ordre.setString(2,recherche);
					ordre.setString(3,filtreCategorie);
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
						articleLu.setDateFinEncheres(rs.getDate("date_debut_encheres").toLocalDate());
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
	public List<ArticleVendu> selectArticlebyCate(LocalDate date, String filtreCategorie) throws DALException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>(); 
		// Recherche de l'utilisateur selon son identifiant dans la Base de donnée
				// 1- Obtenir une connexion
				try (Connection connexion = ConnectionProvider.getConnection();) {
					// 2- Contruire la requete
					PreparedStatement ordre = connexion.prepareStatement(SQL_SELECT_ARTICLE_BY_CATE);
					// ajout du paramètre à la requete(Where pseudo)
					ordre.setDate(1, java.sql.Date.valueOf(date));
					ordre.setString(2,filtreCategorie);
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
						articleLu.setDateFinEncheres(rs.getDate("date_debut_encheres").toLocalDate());
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
					ordre.setString(2,recherche);
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
						articleLu.setDateFinEncheres(rs.getDate("date_debut_encheres").toLocalDate());
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


}
