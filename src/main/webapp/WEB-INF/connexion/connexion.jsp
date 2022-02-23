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
        
        <title>ENI | Enchères | Connexion</title>
    </head>
	<body>
		<!-- Container principal ------------------ -->
		<div class="container">
		
        		<!-- Header ------------------ -->
	    
	            <header class="header">
					<a class="header__link" href="${pageContext.request.contextPath}/home">
						<h1 class="header__title">ENI - Enchères</h1>
					</a>
	            </header>
	                        
            	<!-- Main ------------------ -->
            
	            <main class="main">
                    <h2 class="main__title">Connexion</h2>
                    <!-- Connexion -->
	                <div class="connexion">
	                	<!-- formulaire -->
	                    <form class="connexion__form" action="${pageContext.request.contextPath}/connect" method="post">
	                        <!-- identifiant -->
	                        <div class="identifiant">
	                            <label class="connexion__label" for="identifiant">Identifiant</label>
	                            <input class="connexion__input" type="text" name="identifiant" id="identifiant" value="" placeholder="identifiant">       
	                        </div>
	                        <!-- mot de passe -->
	                        <div class="mdp">
	                            <label class="connexion__label" for="mdp">Mot de passe</label>
	                            <input class="connexion__input" type="password" name="mdp" value="" id="mdp" placeholder="mot de passe">
	                        </div>
	                        <!-- bouton connexion -->
	                        <div class="submit">
	                            <input class="connexion__submit" type="submit" value="Connexion">
	                            <c:if test="${requestScope.La_connexion == false }">
									<p class="connexion__message">La connexion est invalide</p>
								</c:if>
								<!-- se souvenir de moi -->
	                            <div class="remember">
	                                <input class="connexion__checkbox" type="checkbox" name="se-souvenir-de-moi" id="se-souvenir-de-moi">
	                                <label for="connexion__label">Se souvenir de moi</label>
	                                <a class="connexion__link" href="#">Mot de passe oublié</a>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	                
					<!-- créer un compte ------------------ -->
					<a class="main__link" href="${pageContext.request.contextPath}/creer/utilisateur">
						<input class="main__submit" type="submit" name="creercompte" value="Créer un compte">
					</a>
					<a class="main__link" href="${pageContext.request.contextPath}/home">
						<input class="main__submit" type="button" name="annuler" value="Annuler">
					</a>
            </main>
            
            <!--  footer ------------------ -->
            <%@ include file="/WEB-INF/fragments/footer.jspf"%>
        </div>
	</body>
</html>