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
        
		<title>ENI | Enchères | Vendre un article</title>
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

			<main class="main">
				<h2 class="main__title">Nouvelle vente</h2>
				
				<!-- formulaire -->
				<form class="form" method="post" action="${pageContext.request.contextPath}/ajout/article">
	                 	
                 	<!-- nom de l'article -->
                 	<div>
	                 	<label class="form__label" for="nomArticle">Article :</label>
	                    <input class="form__input" type="text" name="nomArticle" id="nomArticle" placeholder="Nom de l'article">
	                    <c:if test="${not empty requestScope.article}">
	                    	<p class="form__error">Champs obligatoire</p>
	                    </c:if>
                 	</div>
                    
                    <!-- description -->
                    <label class="form_label" for="description">Description :</label>
                    <input class="form__input" type="text" name="description" id="description" placeholder="Description">
                    <c:if test="${not empty requestScope.description}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
	                    
	                <!-- categories -->
					<label class="form__label" for="categories">Categories</label>
					<select class="form__select" name="categories" id="categories">
				    <c:forEach items="${listeCategories}" var="categorie" >
				     <option class="form__option" value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
				    </c:forEach>
					</select>
                          
                       <!-- photo de l'article -->
                    <label class="form__label" for="photoArticle">Photo de l'article :</label>
                    <input class="form__input" type="file" name="photoArticle" id="photoArticle" accept="image/png, image/jpeg">
                    <c:if test="${not empty requestScope.photo_article}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
                    
                    <!-- prix initial -->
                    <label class="form__label" for="prixInitial">Mise à prix: </label>
                    <input class="form__input" type="number" name="prixInitial" min="2" max="5000" step="2" value="2">
                    
                    <!-- date de début de l'enchère -->
                    <label class="form__label" for="dateDebutEncheres">Début de l'enchère :</label>
                    <input class="form__input" type="date" name="dateDebutEncheres" id="dateDebutEncheres">
                    <c:if test="${not empty requestScope.date_debut_encheres}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
	                    
	                <!-- date de fin de l'enchère -->    
                    <label class="form__label" for="dateFinEncheres">Fin de l'enchère :</label>
                    <input class="form__input" type="date" name="dateFinEncheres" id="dateFinEncheres">
                    <c:if test="${not empty requestScope.date_fin_encheres}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
                   

                    <!-- rue -->
                    <label class="form__label" for="rue">Rue :</label>
                    <input class="form__input" type="text" name="rue" id="rue" value="${utilisateurActif.getRue()}">
                    <c:if test="${not empty requestScope.rue}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
                    
	                <!-- code postal -->    
                    <label class="form__label" for="codePostal">Code postal :</label>
                    <input class="form__input" type="text" name="codePostal" id="codePostal" value="${utilisateurActif.getCodePostal()}">
                    <c:if test="${not empty requestScope.code_postal}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
                    
	                <!-- article -->
                    <label class="form__class" for="ville">Ville :</label>
                    <input class="form__input" type="text" name="ville" id="ville" value="${utilisateurActif.getVille()}">
                    <c:if test="${not empty requestScope.ville}">
                    	<p class="form__error">Champs obligatoire</p>
                    </c:if>
	                    
	                <!-- article -->
                    <input class="form__button" type="submit" name="enregistrer" value="Enregistrer">
                    <a class="form__link" href="${pageContext.request.contextPath}/home">
                        <input class="form__button" type="button" name="annuler" value="Annuler">
                    </a>
	            </form>
			</main>
		</div>
	</body>
</html>