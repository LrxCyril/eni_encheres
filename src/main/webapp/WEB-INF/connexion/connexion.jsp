<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html>

<html lang="fr">
	<head>
		<head>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet"> 
        
        <title>ENi | Enchères | Connexion</title>
    </head>
	<body>
		<div class="container">
	            <header class="header">
	                <div class="header__logo">
	                    <a class="header__main-link" href="${pageContext.request.contextPath}/home">
	                        <h1 class="header__main-title">ENI - Enchères</h1>
	                    </a>
	                </div>
	            </header>
	            <main class="main">
                <div class="main__name-page">
                    <h2 class="main__title">Connexion</h2>
                </div>
                <div class="main__form">
                    <form class="form__container" action="${pageContext.request.contextPath}/connect" method="post">
                        <div>
                            <label class="form__label" for="recherche-article">Identifiant</label>
                            <input class="form__input" type="text" name="identifiant" id="identifiant" value="" placeholder="identifiant">       
                        </div>
                        <div>
                            <label class="form__label" for="mot-de-passe">Mot de passe</label>
                            <input class="form__input" type="password" name="mdp" value="" id="mdp" placeholder="mot de passe">
                        </div>
                        <div>
                            <input class="form__submit" type="submit" value="Connexion">
                            <c:if test="${requestScope.La_connexion == false }">
								<p class="form__message">La connexion est invalide</p>
							</c:if>
                            <div>
                                <input class="form__checkbox" type="checkbox" name="se-souvenir-de-moi" id="se-souvenir-de-moi">
                                <label for="se-souvenir-de-moi">Se souvenir de moi</label>
                                <a href="#">Mot de passe oublié</a>
                            </div>
                        </div>
                    </form>
                </div>
                <a href="${pageContext.request.contextPath}/creer/utilisateur">
					<input class="form__submit" type="submit" name="creercompte" value="Créer un compte">
				</a>
				<a href="${pageContext.request.contextPath}/home">
                  		<input type="button" name="annuler" value="Annuler">
                </a>
            </main>
            <%@ include file="/WEB-INF/fragments/footer.jspf"%>
        </div>
	</body>
</html>