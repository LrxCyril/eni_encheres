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
				<a class="header__link" href="#">
					<h1 class="header__title">ENI - Enchères</h1>
				</a>
			</header>
			
			<!-- Main ------------------ -->
			
			<div class="main">
				<!-- formulaire de création d'une vente -->
				<form class="form__container" method="post" action="${pageContext.request.contextPath}/detail/enchere">
					<fieldset class="form__fieldset">
						<!-- nom de l'article -->
						<div>
						<label class="form__label" for="nom_article"></label>
						<input type="hidden" name="idArticle" value="${idArticle}">
						<p>${nomArticle}</p>
						</div>
										        
				        <!-- description de la vente -->            
						<div>
						<label class="form__label" for="nom_article">Description:</label>
						<p>${description}</p>
						</div>
				        
				        <!-- meilleure offre -->            
						<div>
						<label class="form__label" for="nom_article">Meilleure offre:</label>
						<p>${meilleureOffre} par ${exEncherisseur} </p>
						</div>
						
						<!-- meise à prix -->            
						<div>
						<label class="form__label" for="nom_article">Mise à prix:</label>
						<p>${miseaPrix}</p>
						</div>
						
			            
			            <!-- Retrais -->              
						<div>
						<label class="form__label" for="nom_article">Retrait</label>
						<p>${adresse}</p>
						<p>${codePostal} ${ville}</p>
						</div>
						
						<!-- Vendeur -->
						<div>
						<label class="form__label" for="nom_article">Vendeur:</label>
						<p>${vendeur}</p>
						</div>
						
						<!-- Tel -->
						<div>
						<label class="form__label" for="nom_article">Tel:</label>
						<p>${tel}</p>
						</div>
						<div>
						<label class="form__label" for="proposition">Ma Proposition</label>
						<input class="form__input" type="text" name="proposition" id="proposition" placeholder="proposition">
						<button type="submit">Enchérir</button>
						</div>
						
					</fieldset>
				</form>
			</div>
		</div>		                
	</body>
</html>