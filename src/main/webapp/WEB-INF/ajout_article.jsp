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
				
					 <form action="${pageContext.request.contextPath}/creer/utilisateur" method="post">
		                <fieldset>
		                 
		                    <label for="nom_article">Article :</label>
		                    <input type="text" name="nom_article" id="nom_article" placeholder="Nom de l'article">
		                    <c:if test="${not empty requestScope.article}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="description">Description :</label>
		                    <input type="text" name="description" id="description" placeholder="Description">
		                    <c:if test="${not empty requestScope.description}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="categorie">Catégorie :</label>
		                    <input type="text" name="no_categorie" id="no_categorie" placeholder="Catégorie">
		                    <c:if test="${not empty requestScope.categorie}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="photo_article">Photo de l'article :</label>
		                    <input type="submit" name="photo_article" id="photo_article" value="UPLOADER">
		                    <c:if test="${not empty requestScope.photo_article}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="date_debut_encheres">Début de l'enchère :</label>
		                    <input type="date" name="date_debut_encheres" id="date_debut_encheres">
		                    <c:if test="${not empty requestScope.date_debut_encheres}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="date_fin_encheres">Fin de l'enchère :</label>
		                    <input type="date" name="date_fin_encheres" id="date_fin_encheres">
		                    <c:if test="${not empty requestScope.date_fin_encheres}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                   
              <!--  --------------------------------------------------------------------------------------  -->    
		                    
		                    <label for="rue">Rue :</label>
		                    <input type="text" name="rue" id="rue" placeholder="Rue">
		                    <c:if test="${not empty requestScope.rue}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="code_postal">Code postal :</label>
		                    <input type="text" name="code_postal" id="code_postal" placeholder="Code postal">
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