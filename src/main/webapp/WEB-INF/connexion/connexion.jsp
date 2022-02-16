<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
	<body>
		<header>
			<h1>ENI-Enchères</h1>
		</header>
		<main>
			<form action="./connect" method="post">
				<input type="text" name="identifiant" value="" placeholder="Email ici..">
				<input type="password" name="mdp" value="" placeholder="Mot de passe ici..">
				<input type="submit" value="Connexion">
				<a href="#" >Mot de passe oublié ?</a>
			</form>
	<!--  <button type="submit" name="creercompte">Créer un compte</button>-->
		</main>
	</body>
</html>