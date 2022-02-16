<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <form action="#" method="post">
                <fieldset>
                    <legend>Mon profil</legend>
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" name="pseudo" id="pseudo" placeholder="pseudo">
                    <label for="nom">Nom :</label>
                    <input type="text" name="nom" id="nom" placeholder="nom">
                    <label for="prenom">Prénom :</label>
                    <input type="text" name="prenom" id="prenom" placeholder="prénom">
                    <label for="email">Email :</label>
                    <input type="text" name="email" id="email" placeholder="email">
                    <label for="telephone">Téléphone :</label>
                    <input type="text" name="telephone" id="telephone" placeholder="numéro de téléphone">
                    <label for="rue">Rue :</label>
                    <input type="text" name="rue" id="rue" placeholder="N°, rue">
                    <label for="code_postal">Code postal :</label>
                    <input type="text" name="code_postal" id="code_postal" placeholder="code postal">
                    <label for="ville">Ville :</label>
                    <input type="text" name="ville" id="ville" placeholder="ville">
                    <label for="motDePasse">Mot de passe :</label>
                    <input type="password" name="motDePasse" id="motDePasse" placeholder="mot de passe">
                    <label for="ConfirmMotDePasse">Confirmation du mot de passe :</label>
                    <input type="password" name="ConfirmMotDePasse" id="ConfirmMotDePasse" placeholder="confirmer votre mot de passe">
                    <input type="submit" name="creer" value="Créer">
                    <a href="#">
                        <input type="button" name="annuler" value="Annuler">
                    </a>
                </fieldset>
            </form>
        </main>
    </body>
</html>