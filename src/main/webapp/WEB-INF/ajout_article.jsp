<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		
		<header>
			<h1>ENI-Enchères</h1>
		</header>
			<main>
				<h2>Nouvelle vente</h2>
				
					 <form action="${pageContext.request.contextPath}/ajout/article" method="post">
		                <fieldset>
		                 
		                    <label for="nomArticle">Article :</label>
		                    <input type="text" name="nomArticle" id="nomArticle" placeholder="Nom de l'article">
		                    <c:if test="${not empty requestScope.article}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="description">Description :</label>
		                    <input type="text" name="description" id="description" placeholder="Description">
		                    <c:if test="${not empty requestScope.description}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="noCategorie">Catégorie :</label>
		                    <input type="text" name="noCategorie" id="noCategorie" placeholder="Catégorie">
		                    <c:if test="${not empty requestScope.categorie}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="photoArticle">Photo de l'article :</label>
		                    <input type="submit" name="photoArticle" id="photoArticle" value="UPLOADER">
		                    <c:if test="${not empty requestScope.photo_article}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="dateDebutEncheres">Début de l'enchère :</label>
		                    <input type="date" name="dateDebutEncheres" id="dateDebutEncheres">
		                    <c:if test="${not empty requestScope.date_debut_encheres}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="dateFinEncheres">Fin de l'enchère :</label>
		                    <input type="date" name="dateFinEncheres" id="dateFinEncheres">
		                    <c:if test="${not empty requestScope.date_fin_encheres}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                   
              <!--  --------------------------------------------------------------------------------------  -->    
		                    
		                    <label for="rue">Rue :</label>
		                    <input type="text" name="rue" id="rue" placeholder="Rue">
		                    <c:if test="${not empty requestScope.rue}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="codePostal">Code postal :</label>
		                    <input type="text" name="codePostal" id="codePostal" placeholder="Code postal">
		                    <c:if test="${not empty requestScope.code_postal}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="ville">Ville :</label>
		                    <input type="text" name="ville" id="ville" placeholder="Ville">
		                    <c:if test="${not empty requestScope.ville}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    
		                    <input type="submit" name="enregistrer" value="Enregistrer">
		                    <a href="${pageContext.request.contextPath}/home">
		                        <input type="button" name="annuler" value="Annuler">
		                    </a>
		                </fieldset>
	            </form>
			</main>
	</body>
</html>