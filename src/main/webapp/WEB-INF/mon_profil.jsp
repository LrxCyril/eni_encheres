<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon Profil</title>
</head>
    <body>
        <header>
            <h1>ENI-Enchères</h1>
        </header>
        <main>
            <h2>Mon profil</h2>
            <form action="${pageContext.request.contextPath}/creer/utilisateur" method="post">
                <fieldset>
                    <legend>Mon profil</legend>
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" name="pseudo" id="pseudo" placeholder="pseudo">
                    <c:if test="${not empty requestScope.pseudo}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="nom">Nom :</label>
                    <input type="text" name="nom" id="nom" placeholder="nom">
                    <c:if test="${not empty requestScope.nom}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="prenom">Prénom :</label>
                    <input type="text" name="prenom" id="prenom" placeholder="prénom">
                    <c:if test="${not empty requestScope.prenom}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="email">Email :</label>
                    <input type="text" name="email" id="email" placeholder="email">
                    <c:if test="${not empty requestScope.email}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="telephone">Téléphone :</label>
                    <input type="text" name="telephone" id="telephone" placeholder="numéro de téléphone">
                    <label for="rue">Rue :</label>
                    <input type="text" name="rue" id="rue" placeholder="N°, rue">
                    <c:if test="${not empty requestScope.rue}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="code_postal">Code postal :</label>
                    <input type="text" name="codePostal" id="codePostal" placeholder="code postal">
					<c:if test="">
						<p>champs obligatoire</p>
					</c:if>                    
                    <label for="ville">Ville :</label>
                    <input type="text" name="ville" id="ville" placeholder="ville">
                   	<c:if test="${not empty requestScope.ville}">
                   		<p>champs oboligatoire</p>
                   	</c:if>
                    <label for="motDePasse">Mot de passe :</label>
                    <input type="password" name="motDePasse" id="motDePasse" placeholder="mot de passe">
                    <c:if test="${not empty requestScope.motDePasse}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <label for="ConfirmMotDePasse">Confirmation du mot de passe :</label>
                    <input type="password" name="confirmMotDePasse" id="confirmMotDePasse" placeholder="confirmer votre mot de passe">
                    <c:if test="${not empty requestScope.confirMotDePasse}">
                    	<p>champs obligatoire</p>
                    </c:if>
                    <input type="submit" name="creer" value="Créer">
                    <a href="${pageContext.request.contextPath}/connect">
                        <input type="button" name="annuler" value="Annuler">
                    </a>
                </fieldset>
            </form>
        </main>
    </body>
</html>