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
        
        <title>ENI | Enchères | Mon profil</title>
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
				<h2 class="main__title">Mon profil</h2>
				<!-- Mes informations -->
				<ul class="main__list"> 
					<li class="main__item">
						<p class="main__text">Pseudo : ${requestScope.pseudo}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Nom : ${requestScope.nom}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Prenom : ${requestScope.prenom}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Email : ${requestScope.email}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Téléphone : ${requestScope.telephone}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Rue : ${requestScope.rue}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Code postal : ${requestScope.codepostal}</p>
					</li>
					<li class="main__item">
						<p class="main__text">Ville : ${requestScope.ville}</p>
					</li>
				</ul>
				
				<!-- boutons -->
				<c:if test="${requestScope.modif}">
					<a class="main__link" href="${pageContext.request.contextPath}/modifier/utilisateur">
						<button class="main__submit" type="submit" >Modifier</button>
					</a>
					<a class="main__link" href="${pageContext.request.contextPath}/supprimer/utilisateur">
						<button class="main__submit" type="submit" >Supprimer</button>
					</a>
				</c:if>
				
				<!-- annuler -->
				<a class="main__link" href="${pageContext.request.contextPath}/home">
					<input class="main__submit" type="button" name="annuler" value="Annuler">
				</a>
			</main>
			<%@ include file="/WEB-INF/fragments/footer.jspf"%>
    	</div>
    </body>
</html>