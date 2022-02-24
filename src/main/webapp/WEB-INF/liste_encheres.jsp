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
      <a class="header__nav-link" href="${pageContext.request.contextPath}/test">Test</a>
        <div class="container">
        
        	<!-- Header ------------------ -->
        
            <header class="header">
				<a class="header__link" href="${pageContext.request.contextPath}/home"> <!-- TODO : Retour à l'accueil | doit rester dans l'espace connecté quand l'utilisateur est identifié -->
					<h1 class="header__title">ENI - Enchères</h1>
				</a>
				<!-- Navigation -->
                <nav class="nav">
                    <ul class="nav__list">
                        <li class="nav__item">
                            <c:if test="${ not sessionScope.session_active}">
                                <a class="nav__link" href="${pageContext.request.contextPath}/connect">Se connecter | S'inscrire</a>
                            </c:if>
                        </li>
                        <c:if test="${sessionScope.session_active}">
	                        <li class="nav__item">
	                            <a class="nav__link" href="${pageContext.request.contextPath}/afficher/encheres">Enchères</a>
	                        </li>
	                        <li class="nav__item">
	                            <a class="nav__link" href="${pageContext.request.contextPath}/ajout/article">Vendre un article</a>
	                        </li>
	                        <li class="nav__item">
	                            <a class="nav__link" href="${pageContext.request.contextPath}/consulter/profil">Mon profil</a>
	                        </li>
	                        <li class="nav__item">
	                            <a class="nav__link" href="${pageContext.request.contextPath}/deconnect">Déconnexion</a>
	                        </li>	
                        </c:if>
                    </ul>
                </nav>
            </header>
            
            <!-- Main ------------------ -->
            
            <main class="main">
				<h2 class="main__title">Liste des enchères</h2>
				<!-- Rechercher un article -->
                <div class="main__research">
                	<!-- formulaire -->
                    <form class="form__research" method="post" action="${pageContext.request.contextPath}/home">
                        <div class="form__fields">
                            <label class="form__label" for="rechercheArticle">Recherche</label>
                            <input class="form__input" type="search" name="rechercheArticle" id="rechercheArticle" placeholder="Le nom de l'article contient">        
                        </div>
                        <div class="form">
                            <label class="form__label" for="categories">Categories</label>
                            <select class="form__select" name="categories" id="categories">
                                <c:forEach items="${listeCategories}" var="categorie" >
	                                <option class="form__option" value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <!-- Filtre ------------------ -->
                        
						<div class="form__filter">
							<c:if test="${session_active}">
							
                            <!-- Achats -->
                            
                            <div class="form__check">
							  <input class="form__input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" onclick="radioDesactive()">
							  	<label class="form__label" for="inlineRadio1">Achats</label>
							  		<ul class="form__list" >
	                                    <li class="form__item" id="liste-achats">
	                                        <input class="form__checkbox" type="checkbox" name="encheres-ouvertes" id="encheres-ouvertes" disabled>
	                                        <label class="form__label" for="encheres-ouvertes">enchères ouvertes</label>
	                                    </li>
	                                    <li class="form__item" id="liste-achats">
	                                        <input class="form__checkbox" type="checkbox" name="mes-encheres" id="mes-encheres" disabled>
	                                        <label class="form__label" for="mes-encheres">mes enchères</label>
	                                    </li>
	                                    <li class="form__item" id="liste-achats">
	                                        <input class="form__checkbox" type="checkbox" name="mes-encheres-remportees" id="mes-encheres-remportees" disabled>
	                                        <label class="form__label" for="mes-encheres-remportees">mes enchères remportées</label>
	                                    </li>
	                                </ul>
							</div>
							
							<!-- Ventes -->
							
							<div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" onclick="radioDesactive()">
							  	<label class="form-check-label" for="inlineRadio2">Ventes</label>
								  	<ul class="form__list" >
		                                    <li class="form__item" id="liste-ventes">
		                                        <input class="form__checkbox" type="checkbox" name="mes-ventes-en-cours" id="mes-ventes-en-cours" disabled>
		                                        <label class="form__label" for="mes-ventes-en-cours">mes ventes en cours</label>
		                                    </li>
		                                    <li class="form__item" id="liste-ventes">
		                                        <input class="form__checkbox" type="checkbox" name="ventes-non-debutees" id="ventes-non-debutees" disabled>
		                                        <label class="form__label" for="ventes-non-debutees">ventes non débutées</label>
		                                    </li>
		                                    <li class="form__item" id="liste-ventes">
		                                        <input class="form__checkbox" type="checkbox" name="ventes-terminees" id="ventes-terminees" disabled>
		                                        <label class="form__label" for="ventes-terminees">ventes terminées</label>
		                                    </li>
	                                </ul>
							</div>
						
                           </c:if>	
                        </div>
                        <div class="form__validate">
                        	<a class="form__link" href="#">
                                <input class="form__submit" type="submit" value="Rechercher">
                            </a>
                        </div>
                    </form>
              	</div>
              	
              	<!-- Cartes annonces ------------------ -->
                <div class="announce">
                	<c:forEach items="${listeArticles}" var="article" >
    					<div class="announce__card">
                        <img class="announce__image" src="#" alt="photo de l'annonce">
                        <div class="announce__description">
                           <c:if test="${session_active}">
                            <a class="announce__link" href="${pageContext.request.contextPath}/detail/enchere?idArticle=${article.getNoArticle()}">
                                <h3 class="announce__title">${article.getNomArticle()}</h3>
                            </a>
                            </c:if>
                             <c:if test="${not session_active}">
                               <h3 class="announce__title">${article.getNomArticle()}</h3>
                            </c:if>
                            
                            <p class="announce__price">Prix : ${article.getMiseAPrix()}</p>
                            <p class="announce__deadline">Fin de l'enchère : ${article.getDateFinEncheres()}</p>
                            <p class="announce__seller">Vendeur : </p>
                            <a class="announce__link" href="${pageContext.request.contextPath}/consulter/profil?utilisateur=${article.getUtilisateur().getPseudo()}">${article.getUtilisateur().getPseudo()}</a>
                        </div>
                    </div>
					</c:forEach>
                </div> 
            </main>
            <%@ include file="/WEB-INF/fragments/footer.jspf"%>
        </div>
    </body>
</html>