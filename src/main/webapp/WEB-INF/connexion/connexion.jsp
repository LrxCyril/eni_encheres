<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>

<%@ include file="../fragments/header.html" %>

<form action="/connexion/connect" method="get">
	<input type="text" name="email" placeholder="Email ici..">
	<input type="text" name="mdp" placeholder="Mot de passe ici..">
	<br>
	<br>
	<button type="submit" name="connexion">Connexion</button> <input type="checkbox" value="SeSouvenir" id="souvenir"> Se souvenir de moi ?</>
	<br>
	<br>
	<a href=".." >Mot de passe oublié ?</a>
</form>
	<br>
	<br>
<button type="submit" name="creercompte">Créer un compte</button>



</body>
</html>