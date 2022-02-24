<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>

<html lang="fr">

	<head>
		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet"> 
        
		<title>ENI | Enchères | Créer une vente</title>
	</head>
	<body>
		<div class="container">
		
			<!-- Header ------------------ -->
			
			<header class="header">
				<%@ include file="/WEB-INF/fragments/logo.jspf"%>
				<!-- Navigation -->
                <%@ include file="/WEB-INF/fragments/nav.jspf"%>
			</header>
			
			<!-- Main ------------------ -->
			
			<main class="main">
				<!-- formulaire de création d'une vente -->
				<form class="form__container" method="post" action="${pageContext.request.contextPath}/detail/enchere">
					<!-- nom de l'article -->
					<div class="hidden">
						<label class="hidden" for="nom_article"></label>
						<input type="hidden" name="idArticle" value="${idArticle}">
						<p>${nomArticle}</p>
					</div>
									        
			        <!-- description de la vente -->            
					<div class="main__announce">
						<h3>A vendre | ${enchereEncours.getArticle().getNomArticle()}</h3>
					</div>
										        
			        <!-- description de la vente -->            
					<div class="main__fields">
						<label class="form__label" for="nom_article">Description :&nbsp;</label>
						<p>${enchereEncours.getArticle().getDescription()}</p>
					</div>
					   <!-- categorie -->            
					<div class="main__fields">
						<label class="form__label" for="nom_article">Categorie :&nbsp;</label>
						<p>categorie ${enchereEncours.getCategorie().getLibelle()}</p>
					</div>
			        <!-- meilleure offre -->            
					<div class="main__fields">
						<label class="form__label" for="nom_article">Meilleure offre :&nbsp;</label>
						<p>${enchereEncours.getEnchere().getMontantEnchere()} crédits par ${enchereEncours.getEncherisseur().getPseudo()} </p>
					</div>

					<!-- mise à prix -->            
					<div class="main__fields">
						<label class="form__label" for="nom_article">Mise à prix :&nbsp;</label>
						<p>${enchereEncours.getArticle().getMiseAPrix()} crédits</p>
					</div>
					
					<div class="main__fields">
						<label class="form__label" for="nom_article">Fin de vente:&nbsp;</label>
						<p>${enchereEncours.getArticle().getDateFinEncheres()} </p>
					</div>
		            
		            <!-- Retrais -->              
					<div class="main__fields">
						<div class="main__pickup">
							<label class="form__label" for="nom_article">Retrait</label>
					
						<!-- Vendeur | adresse (rue et code postal) -->
						<div class="main__fields">
							<p>${enchereEncours.getRetrait().getRue()}</p>
							<p>${enchereEncours.getRetrait().getCodePostal()} ${enchereEncours.getRetrait().getVille()}</p>
						</div>
							
						<!-- Vendeur -->
						<div class="main__fields">
							<label class="form__label" for="nom_article">Vendeur:</label>
							<p>${vendeur}</p>
						</div>
						
						<!-- Tel -->
						<div class="main__fields">
							<p>${enchereEncours.getVendeur().getPseudo()}</p>
						</div>
							
						<!-- Tel -->
						<div class="main__fields">
							<label class="form__label" for="nom_article">Tel:</label>
							<p>${tel}</p>
						</div>
						<div class="main__fields">
							<p>${enchereEncours.getVendeur().getTelephone()}</p>
						</div>
						<div class="main__fields">
							<c:if test="${enchereInvalide}">
								<p>Le montant de l'enchère n'est pas valide ou votre credit est insuffisant</p>
							</c:if>
							<c:if test="${ encherevalide}">
								<p>votre enchère est validée</p>
							</c:if>
							<c:if test="${not utilisateurActif.getPseudo().equals(enchereEncours.getVendeur().getPseudo())}">
								<label class="form__label" for="proposition">Ma Proposition</label>
								<input class="form__input" type="number" name="proposition" id="proposition" placeholder="proposition">
								<button type="submit">Enchérir</button>
							</c:if>
							<c:if test="${utilisateurActif.getPseudo().equals(enchereEncours.getVendeur().getPseudo())}">
							<p>L'enchère sur votre propre article n'est pas authorisée</p>
							</c:if>
						</div>
						<a class="form__link" href="${pageContext.request.contextPath}/home">
							<input class="form__button" type="button" name="annuler" value="Annuler">
           		 		</a>
           		 	</div>
				</form>
			</main>
			<%@ include file="/WEB-INF/fragments/footer.jspf"%>
		</div>		                
	</body>
</html>