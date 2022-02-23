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
				<form class="form__container" method="get" action="${pageContext.request.contextPath}/details/encheres">
					<fieldset class="form__fieldset">
						
						<!-- nom de l'article -->
						<label class="form__label" for="nom_article">${requestScope.}</label>
						<input class="form__input" type="text" name="nom_article" id="nom_article" placeholder="Nom de l'article">
				        
				        <!-- description de la vente -->            
						<label class="form_label" for="description">Description :</label>
						<input class="form__input" type="text" name="description" id="description" placeholder="Description">
				        
				        <!-- catégories -->            
						<label class="form__label" class="form__categories-label" for="categories">Categories</label>
						
						<!-- photo de l'article -->		                            
						<label class="form__label" for="photo_article">Photo de l'article :</label>
						<input class="form__input" type="file" name="photo_article" id="photo_article" accept="image/png, image/jpeg">
			            
			            <!-- nom de l'article -->        
						<label class="form__label" for="prix_initial">Mise à prix: </label>
						<input class="form__input" type="number" min="2" max="5000" step="2" value="2">
						
						<!-- date de début de l'enchère -->
						<label class="form__label" for="date_debut_encheres">Début de l'enchère :</label>
						<input class="form__input" type="date" name="date_debut_encheres" id="date_debut_encheres">
						
						<!-- date de fin de l'enchère --> 
						<label class="form__label" for="date_fin_encheres">Fin de l'enchère :</label>
						<input class="form__input" type="date" name="date_fin_encheres" id="date_fin_encheres">
				       
						<!-- Nouvelle vente --------------------- -->

			            <!-- rue -->       
						<label class="form__label" for="rue">Rue :</label>
						<input class="form__input" type="text" name="rue" id="rue" value="${rueLu}">
						
						<!-- code postal --> 
						<label class="form__label" for="code_postal">Code postal :</label>
						<input class="form__input" type="text" name="code_postal" id="code_postal" value="${cPLu}">
						
						<!-- ville --> 
						<label class="form__class" for="ville">Ville :</label>
						<input class="form__input" type="text" name="ville" id="ville" value="${villeLu}">
					</fieldset>
				</form>
			</div>
		</div>		                
	</body>
</html>