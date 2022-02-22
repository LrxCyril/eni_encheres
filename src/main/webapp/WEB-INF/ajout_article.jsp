<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre un article</title>
</head>
	<body>
		
		<header>
			<h1>ENI-Enchères</h1>
		</header>
			<main class="main">
				<div class="main__name-page">
					<h2 class="main__title">Nouvelle vente</h2>
				</div>
				<div class="main__form">
							 <form class="form__container" method="post" action="${pageContext.request.contextPath}/ajout/article">
				                <fieldset class="form__fieldset">
				                 
				                    <label class="form__label" for="nom_article">Article :</label>
				                    <input class="form__input" type="text" name="nom_article" id="nom_article" placeholder="Nom de l'article">
				                    <c:if test="${not empty requestScope.article}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    <label class="form_label" for="description">Description :</label>
				                    <input class="form__input" type="text" name="description" id="description" placeholder="Description">
				                    <c:if test="${not empty requestScope.description}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    <label class="form__label" class="form__categories-label" for="categories">Categories</label>
		                            <select class="form__select" name="categories" id="categories">
		                                <c:forEach items="${listeCategories}" var="categorie" >
			                                <option class="form__option" value="${categorie}">${categorie}</option>
		                                </c:forEach>
		                            </select>
				                    <label class="form__label" for="photo_article">Photo de l'article :</label>
				                    <input class="form__input" type="file" name="photo_article" id="photo_article" accept="image/png, image/jpeg">
				                    <c:if test="${not empty requestScope.photo_article}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    
				                    <label class="form__label" for="prix_initial">Mise à prix: </label>
				                    <input class="form__input" type="number" min="2" max="5000" step="2" value="2">
				                    
				                    <label class="form__label" for="date_debut_encheres">Début de l'enchère :</label>
				                    <input class="form__input" type="date" name="date_debut_encheres" id="date_debut_encheres">
				                    <c:if test="${not empty requestScope.date_debut_encheres}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    <label class="form__label" for="date_fin_encheres">Fin de l'enchère :</label>
				                    <input class="form__input" type="date" name="date_fin_encheres" id="date_fin_encheres">
				                    <c:if test="${not empty requestScope.date_fin_encheres}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                   
		              <!--  --------------------------------------------------------------------------------------  -->    
				                    
				                    <label class="form__label" for="rue">Rue :</label>
				                    <input class="form__input" type="text" name="rue" id="rue" value="${rueLu}">
				                    <c:if test="${not empty requestScope.rue}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    <label class="form__label" for="code_postal">Code postal :</label>
				                    <input class="form__input" type="text" name="code_postal" id="code_postal" value="${cPLu}">
				                    <c:if test="${not empty requestScope.code_postal}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    <label class="form__class" for="ville">Ville :</label>
				                    <input class="form__input" type="text" name="ville" id="ville" value="${villeLu}">
				                    <c:if test="${not empty requestScope.ville}">
				                    	<p>Champs obligatoire</p>
				                    </c:if>
				                    
				                    <input class="form__button" type="submit" name="enregistrer" value="Enregistrer">
				                    <a href="${pageContext.request.contextPath}/home">
				                        <input class="form__button" type="button" name="annuler" value="Annuler">
				                    </a>
				                </fieldset>
			            </form>
		         	</div>
			</main>
	</body>
</html>