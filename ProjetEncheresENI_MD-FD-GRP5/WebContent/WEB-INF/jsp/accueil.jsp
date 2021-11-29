<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="../inclusions/header.jspf" %>

<main class="flex-shrink-0">
<div class="container">

<c:forEach var="article" items="${articles }">
<form method="get" action="encherir">
<input type="hidden" name="articleNumber" value="${article.idArticle }">
<p>Nom de l'article : <input type="submit" class="btn btn-link" value="${article.nomArticle }"></p>
</form>

<p>Description de l'article : ${article.description }</p>
<p>Début de l'enchère : ${article.dateDebut }</p>
<p>Fin de l'enchère : ${article.dateFin }</p>
<p>Mise à prix : ${article.prixInitial }</p>
<form method="get" action="detailUtilisateur">
<input type="hidden" name="userNumber" value="${article.utilisateur.idUtilisateur }">
<p>Vendeur : <input type="submit" class="btn btn-link" value="${article.utilisateur.pseudo }"></p>
</form>
<p>Catégorie : ${article.categorie.libelle }</p>
<p>Image : ${article.urlImage }</p>
<hr>
</c:forEach>
</div>




</main>	
<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>