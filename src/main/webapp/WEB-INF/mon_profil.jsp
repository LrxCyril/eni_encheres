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
				<a class="header__link" href="#">
					<h1 class="header__title">ENI - Enchères</h1>
				</a>
            </header>
            
            <!-- Main ------------------ -->
			<main class="main">
				<h2 class="main__title">Mon profil</h2>
				
				<!-- formulaire : créer un compte -->
				<form class="form" action="${pageContext.request.contextPath}/creer/utilisateur" method="post">
					<fieldset class="form__fieldset">
						<c:if test="${requestScope.userExist}">
							<p class="form__text">Cet utilisateur existe déja</p>
						</c:if>
						<legend class="form__legend">Mon profil</legend>
						
						<!-- pseudo -->
						<label class="form__label" for="pseudo">Pseudo :</label>
						<input class="form__input" type="text" name="pseudo" id="pseudo" placeholder="pseudo" value="${utilisateurActif.getPseudo()}" >
						<c:if test="${not empty requestScope.pseudo}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- nom -->
						<label class="form__label" for="nom">Nom :</label>
						<input class="form__input" type="text" name="nom" id="nom" placeholder="nom" value="${utilisateurActif.getNom()}">
						<c:if test="${not empty requestScope.nom}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- prenom -->
						<label class="form__label" for="prenom">Prénom :</label>
						<input class="form__input" type="text" name="prenom" id="prenom" placeholder="prénom" value="${utilisateurActif.getPrenom()}">
						<c:if test="${not empty requestScope.prenom}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- email -->
						<label class="form__label" for="email">Email :</label>
						<input class="form__input" type="text" name="email" id="email" placeholder="email" value="${utilisateurActif.getEmail()}">
						<c:if test="${not empty requestScope.email}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- telephone -->
						<label class="form__label" for="telephone">Téléphone :</label>
						<input class="form__input" type="text" name="telephone" id="telephone" placeholder="numéro de téléphone" value="${utilisateurActif.getTelephone()}">
						
						<!-- rue -->
						<label class="form__label" for="rue">Rue :</label>
						<input class="form__input" type="text" name="rue" id="rue" placeholder="N°, rue" value="${utilisateurActif.getRue()}">
						<c:if test="${not empty requestScope.rue}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- code postal -->
						<label class="form__label" for="codePostal">Code postal :</label>
						<input class="form__input" type="text" name="codePostal" id="codePostal" placeholder="code postal" value="${utilisateurActif.getCodePostal()}">
						<c:if test="">
							<p class="form__error">champs obligatoire</p>
						</c:if> 
						
						<!-- ville -->                   
						<label class="form__label" for="ville">Ville :</label>
						<input class="form__input" type="text" name="ville" id="ville" placeholder="ville" value="${utilisateurActif.getVille()}">
						<c:if test="${not empty requestScope.ville}">
							<p class="form__error">champs oboligatoire</p>
						</c:if>
						
						<!-- mot de passe -->
						<label class="form__label" for="motDePasse">Mot de passe :</label>
						<input class="form__input" type="password" name="motDePasse" id="motDePasse" placeholder="mot de passe" value="${utilisateurActif.getMotDePasse() }}">
						<c:if test="${not empty requestScope.motDePasse}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- confirmation du mot de passe -->
						<label class="form__label" for="ConfirmMotDePasse">Confirmation du mot de passe :</label>
						<input class="form__input" type="password" name="confirmMotDePasse" id="confirmMotDePasse" value="${utilisateurActif.getMotDePasse() }" >
						<c:if test="${not empty requestScope.confirMotDePasse}">
							<p class="form__error">champs obligatoire</p>
						</c:if>
						
						<!-- boutons -->
						<c:if test="${requestScope.creer}">
							<input class="form__input" type="submit" name="creer" value="Créer">
							<a class="form__link" href="${pageContext.request.contextPath}/connect">
								<input class="form__input" type="button" name="annuler" value="Annuler">
							</a>
						</c:if>
						
						<!-- annuler -->
						<c:if test="${not requestScope.creer}">
							<input class="form__input" type="submit" name="modifier" value="Enregistrer">
							<a class="form__link" href="${pageContext.request.contextPath}/home">
								<input class="form__input" type="button" name="annuler" value="Annuler">
							</a>
						</c:if>
					</fieldset>
				</form>
			</main>
		</div>
    </body>
</html>