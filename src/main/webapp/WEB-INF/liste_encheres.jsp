<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
	<body>
	
		<header>
			<h1>ENI-Enchères</h1>
		</header>
	
			<h1>Liste des enchères</h1>
				<ul>
					<li>
				 		<c:if test="${ not sessionScope.session_active}">
							<a href="${pageContext.request.contextPath}/connect">S'inscrire / Se connecter</a>
						</c:if>
						
						<c:if test="${sessionScope.session_active}">
							<a href="${pageContext.request.contextPath}/afficher/encheres">Enchères</a>
							<a href="${pageContext.request.contextPath}/vendre/article">Vendre un article</a>
							<a href="${pageContext.request.contextPath}/consulter/profil">Mon profil</a>
			            	<a href="${pageContext.request.contextPath}/deconnect">Déconnexion</a>	
			            </c:if>
					</li>	
				</ul>
	</body>
</html>