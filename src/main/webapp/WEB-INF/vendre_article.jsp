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
		                 
		                    <label for="article">Article :</label>
		                    <input type="text" name="nom_article" id="article" placeholder=" ">
		                    <c:if test="${not empty requestScope.article}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="description">Description :</label>
		                    <input type="text" name="description" id="description" placeholder=" ">
		                    <c:if test="${not empty requestScope.description}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="categorie">Catégorie :</label>
		                    <input type="text" name="no_categorie" id="no_categorie" placeholder=" ">
		                    <c:if test="${not empty requestScope.prenom}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="photo_article">Photo de l'article :</label>
		                    <input type="submit" name="photo_article" id="photo_article" value="UPLOADER">
		                    <c:if test="${not empty requestScope.email}">
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
		                    <label for="telephone">Téléphone :</label>
		                    <input type="text" name="telephone" placeholder="numéro de téléphone">
		                    
		                    
              <!--  --------------------------------------------------------------------------------------  -->    
		                    
		                    
		                    <label for="rue">Rue :</label>
		                    <input type="text" name="rue" id="rue">
		                    <c:if test="${not empty requestScope.rue}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="code_postal">Code postal :</label>
		                    <input type="text" name="code_postal" id="code_postal">
		                    <c:if test="${not empty requestScope.code_postal}">
		                    	<p>Champs obligatoire</p>
		                    </c:if>
		                    <label for="ville">Ville :</label>
		                    <input type="text" name="ville" id="ville">
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