<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
	<body>
		<div class="main__form">
					<form class="form__container" method="get" action="${pageContext.request.contextPath}/details/encheres">
		                <fieldset class="form__fieldset">
		                 
		                    <label class="form__label" for="nom_article">${requestScope.}</label>
		                    <input class="form__input" type="text" name="nom_article" id="nom_article" placeholder="Nom de l'article">
			                    
		                    <label class="form_label" for="description">Description :</label>
		                    <input class="form__input" type="text" name="description" id="description" placeholder="Description">
			                    
		                    <label class="form__label" class="form__categories-label" for="categories">Categories</label>
                            
		                    <label class="form__label" for="photo_article">Photo de l'article :</label>
		                    <input class="form__input" type="file" name="photo_article" id="photo_article" accept="image/png, image/jpeg">
			                    
		                    
		                    <label class="form__label" for="prix_initial">Mise à prix: </label>
		                    <input class="form__input" type="number" min="2" max="5000" step="2" value="2">
		                    
		                    <label class="form__label" for="date_debut_encheres">Début de l'enchère :</label>
		                    <input class="form__input" type="date" name="date_debut_encheres" id="date_debut_encheres">
			                    
		                    <label class="form__label" for="date_fin_encheres">Fin de l'enchère :</label>
		                    <input class="form__input" type="date" name="date_fin_encheres" id="date_fin_encheres">
			                    
		                   
              <!--  --------------------------------------------------------------------------------------  -->    
		                    
		                    <label class="form__label" for="rue">Rue :</label>
		                    <input class="form__input" type="text" name="rue" id="rue" value="${rueLu}">
			                    
		                    <label class="form__label" for="code_postal">Code postal :</label>
		                    <input class="form__input" type="text" name="code_postal" id="code_postal" value="${cPLu}">
			                    
		                    <label class="form__class" for="ville">Ville :</label>
		                    <input class="form__input" type="text" name="ville" id="ville" value="${villeLu}">
			                    











	</body>
</html>