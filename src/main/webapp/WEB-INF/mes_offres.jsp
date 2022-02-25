<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
        
        <script type="text/javascript" src="./radioDesactive.js"></script>
        
        <title>ENI | Enchères</title>
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
				<h2 class="main__title">Liste des enchères</h2>
				<!-- Rechercher un article -->
                        <div class="form">
                            <label class="form__label" for="categories">Historique de mes offres</label>
                                <p>${utilisateurActif.getNom()} ${utilisateurActif.getPrenom()}</p>
                                <p>voici l'etat de vos enchères</p>
                                <c:forEach items="${mesOffres}" var="offre" >
                                   <ul>
	                                   <li>
	                                   		<c:if test="${offre.getEtatVente()==0}">
			                                    <a class="announce__link" href="${pageContext.request.contextPath}/detail/enchere?idArticle=${offre.getNoArticle()}">
			                              		  <h3 class="announce__title">${offre.getNomArticle()}</h3>
			                           			</a>
											</c:if>
	                                   		<c:if test="${offre.getEtatVente()==10}">
			                                    <a class="announce__link" href="${pageContext.request.contextPath}/detail/enchere?idArticle=${offre.getNoArticle()}&EtatVente=${offre.getEtatVente()}">
			                              		  <h3 class="announce__title">${offre.getNomArticle()}</h3>
			                           			</a>
											</c:if>
	                                   		<c:if test="${offre.getEtatVente()==11}">
			                                     <h3 class="announce__title">${offre.getNomArticle()}</h3>
											</c:if>
	                           			</li>
	                           			<li>	
	                           				<p>Statut: ${offre.getEtatVenteTxt()} Date de Fin: ${offre.getDateFinEncheres()}</p>
	                           			</li>
	                           			<li>
		                                	<p> Mon Offre: ${offre.getMonOffre()} </p>
		                                </li>
                                   	</ul>
                                </c:forEach>
                        </div>
					<a class="form__link" href="${pageContext.request.contextPath}/home">
						<input class="form__submit" type="button" name="annuler" value="Retour Accueil">
					</a>
            </main>
            <%@ include file="/WEB-INF/fragments/footer.jspf"%>
        </div>
    </body>
</html>