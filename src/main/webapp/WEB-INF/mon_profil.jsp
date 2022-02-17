<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="../css/reset.css">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../fontawesome-free-5.15.4-web/css/all.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet"> 
        
        <title>Mon profil | ENi | Enchères</title>
    </head>
    <body>
        <div class="container">
            <header class="header">
                <div class="header__logo">
                    <a class="header__main-link" href="index.html">
                        <h1 class="header__main-title">ENI - Enchères</h1>
                    </a>
                </div>
            </header>
        <main class="main">
            <h2 class="main__title">Mon profil</h2>
            <form class="form__container" action="${pageContext.request.contextPath}/creer/utilisateur" method="post">
                <fieldset class="form__fieldset">
                    <legend class="form__legend">Mon profil</legend>
                    <label class="form__label" for="pseudo">Pseudo :</label>
                    <input class="form__input" type="text" name="pseudo" id="pseudo" placeholder="pseudo" value="${requestScope.pseudoLu}" >
                    <c:if test="${not empty requestScope.pseudo}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="nom">Nom :</label>
                    <input class="form__input" type="text" name="nom" id="nom" placeholder="nom" value="${requestScope.nomLu}">
                    <c:if test="${not empty requestScope.nom}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="prenom">Prénom :</label>
                    <input class="form__input" type="text" name="prenom" id="prenom" placeholder="prénom" value="${requestScope.prenomLu}">
                    <c:if test="${not empty requestScope.prenom}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="email">Email :</label>
                    <input class="form__input" type="text" name="email" id="email" placeholder="email" value="${requestScope.emailLu}">
                    <c:if test="${not empty requestScope.email}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="telephone">Téléphone :</label>
                    <input class="form__input" type="text" name="telephone" id="telephone" placeholder="numéro de téléphone" value="${requestScope.telLu}">
                    <label for="rue">Rue :</label>
                    <input class="form__input" type="text" name="rue" id="rue" placeholder="N°, rue" value="${requestScope.rueLu}">
                    <c:if test="${not empty requestScope.rue}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="code_postal">Code postal :</label>
                    <input class="form__input" type="text" name="codePostal" id="codePostal" placeholder="code postal" value="${requestScope.cPLu}">
					<c:if test="">
						<p class="form__error">champs obligatoire</p>
					</c:if>                    
                    <label for="ville">Ville :</label>
                    <input class="form__input" type="text" name="ville" id="ville" placeholder="ville" value="${requestScope.villeLu}">
                   	<c:if test="${not empty requestScope.ville}">
                   		<p class="form__error">champs oboligatoire</p>
                   	</c:if>
                    <label for="motDePasse">Mot de passe :</label>
                    <input class="form__input" type="password" name="motDePasse" id="motDePasse" placeholder="mot de passe" value="${requestScope.mdpLu}">
                    <c:if test="${not empty requestScope.motDePasse}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <label for="ConfirmMotDePasse">Confirmation du mot de passe :</label>
                    <input class="form__input" type="password" name="confirmMotDePasse" id="confirmMotDePasse" value="${requestScope.mdpLu}" >
                    <c:if test="${not empty requestScope.confirMotDePasse}">
                    	<p class="form__error">champs obligatoire</p>
                    </c:if>
                    <c:if test="${requestScope.creer}">
                   		 <input type="submit" name="creer" value="Créer">
                    </c:if>
                     <c:if test="${not requestScope.creer}">
                   		 <input type="submit" name="modifier" value="Modifier">
                    </c:if>
                    <a href="${pageContext.request.contextPath}/connect">
                        <input type="button" name="annuler" value="Annuler">
                    </a>
                </fieldset>
            </form>
        </main>
        </div>
    </body>
</html>