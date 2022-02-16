<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

	<body>
	
		<header>
			<h1>ENI-Enchères</h1>
		</header>
	
		<form action="./connect" method="post">
			<input type="text" name="identifiant" value="" placeholder="Email ici..">
			<input type="text" name="mdp" value="" placeholder="Mot de passe ici..">
			<br>
			<br>
			<input type="submit" value="Connexion">
			<br>
				<c:if test="${requestScope.La_connexion == false }">
					<p>La connexion est invalide </p>
				</c:if>
				
			<br>
			<br>
			<a href="#" >Mot de passe oublié ?</a>
		</form>
			<br>
			<br>
	<!--  <button type="submit" name="creercompte">Créer un compte</button>-->
	
	</body>
</html>