<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet"> 
        
        <title>ENi | Enchères</title>
    </head>
    <body>
        <div class="container">
            <header class="header">
                <div class="header__logo">
                    <a class="header__main-link" href="${pageContext.request.contextPath}/home"> <!-- TODO : Retour à l'accueil | doit rester dans l'espace connecté quand l'utilisateur est identifié -->
                        <h1 class="header__main-title">ENI - Enchères</h1>
                    </a>
                </div>
                <nav class="header__nav">
                    <ul class="header__nav-list">
                        <li class="header__nav-item">
                            <c:if test="${ not sessionScope.session_active}">
                                <a class="header__nav-link" href="${pageContext.request.contextPath}/connect">Se connecter | S'inscrire</a>
                            </c:if>
                        </li>
                        <c:if test="${sessionScope.session_active}">
	                        <li>
	                            <a class="header__nav-link" href="${pageContext.request.contextPath}/afficher/encheres">Enchères</a>
	                        </li>
	                        <li>
	                            <a class="header__nav-link" href="${pageContext.request.contextPath}/vendre/article">Vendre un article</a>
	                        </li>
	                        <li>
	                            <a class="header__nav-link" href="${pageContext.request.contextPath}/consulter/profil">Mon profil</a>
	                        </li>
	                        <li>
	                            <a class="header__nav-link" href="${pageContext.request.contextPath}/deconnect">Déconnexion</a>
	                        </li>	
                        </c:if>
                    </ul>
                </nav>
            </header>
            <main class="main">
                <div class="main__name-page">
                    <h2 class="main__title">Liste des enchères</h2>
                </div>
                <div class="main__form">
                    <form class="form__container" action="#">
                        <div>
                            <label class="form__label" for="recherche-article">Recherche</label>
                            <input class="form__input" type="search" name="recherche-article" id="recherche-article" placeholder="Le nom de l'article contient">        
                        </div>
                        <div>
                            <label class="form__categories-label" for="categories">Categories</label>
                            <select class="form__select" name="categories" id="categories">
                                <option class="form__option" value="toutes">Toutes</option>
                                <option class="form__option" value="chambre">Chambre</option>
                                <option class="form__option" value="equipement-cuisine">Equipement Cuisine</option>
                                <option class="form__option" value="equipement">Equipement</option>
                                <option class="form__option" value="salle-de-bain">Salle de bain</option>
                                <option class="form__option" value="salon">Salon</option>
                            </select>
                        </div>
                        <!-- TODO : afficher les boutons radios et checkbox liés aux enchères quand je suis redirigé  sur mon espace utilisateur-->
<!-- 
                        		<div class="form__filter">
                            <div class="form__achats-filter">
                                <input class="form__radio" type="radio" name="achats" id="achats" checked>
                                <label class="form__radio-label" for="achats">Achats</label>
                                <ul class="form__list">
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="encheres-ouvertes" id="encheres-ouvertes">
                                        <label class="form__checkbox-label" for="encheres-ouvertes">enchères ouvertes</label>
                                    </li>
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="mes-encheres" id="mes-encheres">
                                        <label class="form__checkbox-label" for="mes-encheres">mes enchères</label>
                                    </li>
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="mes-encheres-remportees" id="mes-encheres-remportees">
                                        <label class="form__checkbox-label" for="mes-encheres-remportees">mes enchères remportées</label>
                                    </li>
                                </ul>
                            </div>
                            <div class="form__ventes-filter">
                                <input class="form__radio" type="radio" name="mes-ventes" id="mes-ventes">
                                <label class="form__radio-label" for="mes-ventes">Mes ventes</label>
                                <ul class="form__list">
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="mes-ventes-en-cours" id="mes-ventes-en-cours">
                                        <label class="form__checkbox-label" for="mes-ventes-en-cours">mes ventes en cours</label>
                                    </li>
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="ventes-non-debutees" id="ventes-non-debutees">
                                        <label class="form__checkbox-label" for="ventes-non-debutees">ventes non débutées</label>
                                    </li>
                                    <li class="form__item">
                                        <input class="form__checkbox" type="checkbox" name="ventes-terminees" id="ventes-terminees">
                                        <label class="form__checkbox-label" for="ventes-terminees">ventes terminées</label>
                                    </li>
                                </ul>
                            </div>
                            <a class="form__link" href="#">
                                <input class="form__submit" type="submit" value="Rechercher">
                            </a>
                        </div>
-->
                    </form>
                </div>
                <div class="announce">
                    <div class="announce__card">
                        <img class="announce__image" src="#" alt="photo de l'annonce">
                        <div class="announce__description">
                            <a class="announce__link" href="#">
                                <h3 class="announce__title">PC Gamer pour travailler</h3>
                            </a>
                            <p class="announce__price">Prix : 210 Points</p>
                            <p class="announce__deadline">Fin de l'enchère : 10/08/2018</p>
                            <p class="announce__seller-label">Vendeur : </p>
                            <!-- TODO : renvoi vers la page profil de l'annonceur, consultation -->
                            <a class="announce__seller-profile" href="#">jojo44</a>
                        </div>
                    </div>
                    <div class="announce__card">
                        <img class="announce__image" src="#" alt="photo de l'annonce">
                        <div class="announce__description">
                            <a class="announce__link" href="#">
                                <h3 class="announce__title">Rocket stove pour riz et pâtes</h3>
                            </a>
                            <p class="announce__price">Prix : 185 Points</p>
                            <p class="announce__deadline">Fin de l'enchère : 09/10/2018</p>
                            <p class="announce__seller-label">Vendeur : </p>
                            <!-- TODO : renvoi vers la page profil de l'annonceur, consultation -->
                            <a class="announce__seller-profile" href="#">jiji56</a>
                        </div>
                    </div>
                </div> 
            </main>
        </div>
    </body>
</html>