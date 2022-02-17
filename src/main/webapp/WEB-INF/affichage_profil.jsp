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
				<ul> 
                   <li><p>Pseudo : ${requestScope.pseudo}</p></li>
                   <li><p>nom : ${requestScope.nom}</p></li>
                   <li><p>prenom : ${requestScope.prenom}</p></li>
                   <li><p>email : ${requestScope.email}</p></li>
                   <li><p>telephone : ${requestScope.telephone}</p></li>
                   <li><p>rue : ${requestScope.rue}</p></li>
                   <li><p>codepostal : ${requestScope.codepostal}</p></li>
                   <li><p>ville : ${requestScope.ville}</p></li>
                  
				</ul>
					<c:if test="${requestScope.modif}">
						<form action="${pageContext.request.contextPath}/creer/utilisateur" method="get">
							 <input type="hidden" name="modif" value="ok">
							 <input type="submit" name="modifier" value="Modifier">
						</form>
                    </c:if>
        </main>
    </body>
</html>