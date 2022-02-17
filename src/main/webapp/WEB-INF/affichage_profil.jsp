<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- liens avec le CSS -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/reset.css">
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
	    
	    <!-- import ddes fonts depuis Google fonts -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet"> 
		
		<title>ENi | Enchères | Profil</title>
	</head>
    <body>
        <header class="header">
            <h1 class="header__main-title">ENI-Enchères</h1>
        </header>
        <main class="main">
			<ul> 
	            <li>
	            	<p>Pseudo : ${requestScope.pseudo}</p>
	            </li>
	            <li>
	            	<p>nom : ${requestScope.nom}</p>
	            </li>
	            <li>
	            	<p>prenom : ${requestScope.prenom}</p>
	            </li>
	            <li>
	            	<p>email : ${requestScope.email}</p>
	            </li>
	            <li>
	            	<p>telephone : ${requestScope.telephone}</p>
	            </li>
	            <li>
	            	<p>rue : ${requestScope.rue}</p>
	            </li>
	            <li>
	           		<p>codepostal : ${requestScope.codepostal}</p>
	            </li>
	            <li>
	            	<p>ville : ${requestScope.ville}</p>
	            </li>  
			</ul>
			<c:if test="${requestScope.modif}">
				<a href="${pageContext.request.contextPath}/modifier/utilisateur">
					<button type="submit" >Modifier</button>
				</a>
				<a href="${pageContext.request.contextPath}/supprimer/utilisateur">
					<button type="submit" >Supprimer</button>
				</a>
			</c:if>
        </main>
    </body>
</html>