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
        
        <title>Mon profil | ENi | Enchères</title>
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
				
				<!-- formulaire : créer un compte -->
				<form class="form" action="${pageContext.request.contextPath}/creer/utilisateur" method="post">
					<c:if test="${requestScope.userExist}">
						<p class="form__text">Cet utilisateur existe déja</p>
					</c:if>
					
					<!-- pseudo -->
					<div class="form__fields">
						<label class="form__label" for="pseudo">Pseudo :</label>
						<input class="form__input" type="text" name="pseudo" id="pseudo" placeholder="pseudo" value="${utilisateurActif.getPseudo()}" required="required">
					</div>
					
					
					<!-- nom -->
					<div class="form__fields">
						<label class="form__label" for="nom">Nom :</label>
						<input class="form__input" type="text" name="nom" id="nom" placeholder="nom" value="${utilisateurActif.getNom()}"required="required">
					</div>
					
					
					<!-- prenom -->
					<div class="form__fields">
						<label class="form__label" for="prenom">Prénom :</label>
						<input class="form__input" type="text" name="prenom" id="prenom" placeholder="prénom" value="${utilisateurActif.getPrenom()}" required="required">
					</div>
					
					
					<!-- email -->
					<div class="form__fields">
						<label class="form__label" for="email">Email :</label>
						<input class="form__input" type="email" name="email" id="email" placeholder="email" value="${utilisateurActif.getEmail()}"required="required">
					</div>
					
					
					<!-- telephone -->
					<div class="form__fields">
						<label class="form__label" for="telephone">Téléphone :</label>
						<input class="form__input" type="tel" name="telephone" id="telephone" placeholder="numéro de téléphone" value="${utilisateurActif.getTelephone()}">
					</div>
					
					<!-- rue -->
					<div class="form__fields">
						<label class="form__label" for="rue">Rue :</label>
						<input class="form__input" type="text" name="rue" id="rue" placeholder="N°, rue" value="${utilisateurActif.getRue()}"required="required">
					</div>
					
					
					<!-- code postal -->
					<div class="form__fields">
						<label class="form__label" for="codePostal">Code postal :</label>
						<input class="form__input" type="text" name="codePostal" id="codePostal" placeholder="code postal" value="${utilisateurActif.getCodePostal()}"required="required">
					</div>
					<!-- ville -->
					<div class="form__fields">
						<label class="form__label" for="ville">Ville :</label>
						<input class="form__input" type="text" name="ville" id="ville" placeholder="ville" value="${utilisateurActif.getVille()}"required="required">
					</div>                   
					<!-- mot de passe -->
					<div class="form__fields">
						<label class="form__label" for="motDePasse">Mot de passe :</label>
						<input class="form__input" type="password" name="motDePasse" id="motDePasse" placeholder="mot de passe" value="${utilisateurActif.getMotDePasse()}"required="required">
					</div>
					
					<!-- confirmation du mot de passe -->
					<div class="form__fields">
						<label class="form__label" for="ConfirmMotDePasse">Confirmation du mot de passe :</label>
						<input class="form__input" type="password" name="confirmMotDePasse" id="confirmMotDePasse" value="${utilisateurActif.getMotDePasse()}" required="required">
					</div>
						<c:if test="${erreur!=null}">
							<p>${erreur}</p>
						</c:if>
					<!-- boutons -->
					
					<!-- enregistrer -->
					<div class="form__fields">
						<c:if test="${not requestScope.creer}">
							<input class="form__submit" type="submit" name="modifier" value="Enregistrer">
						</c:if>
					</div>
					<!-- annuler -->
					<div class="form__fields">
						<c:if test="${not requestScope.creer}">
							<a class="form__link" href="${pageContext.request.contextPath}/home">
								<input class="form__submit" type="button" name="annuler" value="Annuler">
							</a>
						</c:if>
					</div>
					
					<!-- annuler if -->
					<div class="form__fields">
						<c:if test="${requestScope.creer}">
							<input class="form__submit" type="submit" name="creer" value="Créer">
						</c:if>
					</div>
					<div class="form__fields">
						<c:if test="${requestScope.creer}">
							<a class="form__link" href="${pageContext.request.contextPath}/connect">
								<input class="form__submit" type="button" name="annuler" value="Annuler">
							</a>
						</c:if>
					</div>
				</form>
			</main>
			<%@ include file="/WEB-INF/fragments/footer.jspf"%>
		</div>
    </body>
</html>