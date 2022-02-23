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
		<div class="container">
			<header class="header">
				<a class="header__link" href="${pageContext.request.contextPath}/home">
					<h1 class="header__title">ENI - Enchères</h1>
				</a>
            </header>
            <main class="main">
                   <h2 class="main__title">Connexion</h2>
                <div class="main__container">
                    <form class="form__connexion" action="${pageContext.request.contextPath}/connect" method="post">
                        <div class="form__fields">
                            <label class="form__label" for="identifiant">Identifiant</label>
                            <input class="form__input" type="text" name="identifiant" id="identifiant" value="" placeholder="identifiant">       
						</div>
						<div class="form__fields">
							<label class="form__label" for="mdp">Mot de passe</label>
							<input class="form__input" type="password" name="mdp" value="" id="mdp" placeholder="mot de passe">
						</div>
						<div class="form__send">
							<div class="form__remember">
								<div class="form__remember-check">
									<input class="form__checkbox" type="checkbox" name="se-souvenir-de-moi" id="se-souvenir-de-moi">
									<label for="form__label">Se souvenir de moi</label>
								</div>
								<a class="form__link" href="#">Mot de passe oublié</a>
							</div>
							<input class="form__submit" type="submit" value="Connexion">
							<c:if test="${requestScope.La_connexion == false }">
								<p class="form__message">La connexion est invalide</p>
							</c:if>
						</div>
					</form>
				</div>
				<div class="main__container">
					<div class="main__redirection">
						<p class="main__text">Nouveau membre ?</p>
						<div class="main__subcontainer">
							<a class="main__link" href="${pageContext.request.contextPath}/creer/utilisateur">
								<input class="main__submit" type="submit" name="creercompte" value="Créer un compte">
							</a>
							<a class="main__link" href="${pageContext.request.contextPath}/home">
								<input class="main__submit" type="button" name="annuler" value="Annuler">
							</a>
						</div>
					</div>
				</div>
			</main>
			<%@ include file="/WEB-INF/fragments/footer.jspf"%>
		</div>
	</body>
</html>