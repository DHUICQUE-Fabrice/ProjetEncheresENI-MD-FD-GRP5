<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.LecteurMessages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI Enchères.</title>
</head>
<body class="container-fluid">
	<%@ include file="../inclusions/header.jspf"%>
	<h2>Détails de la vente.</h2>
	<div class="row">
		<nav id="sideMenu" class="col-12 col-sm-4 col-md-4">
			<img src="img/image_default.jpg" width="200" height="200" />
		</nav>
		<div id="grilleChoix" class="col-12 col-sm-8 col-md-8">
			<form method="post" action="<%=request.getContextPath()%>/encherir">
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Article :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-4 text-secondary">${article.nomArticle}</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Description :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-4 text-secondary">${article.description}</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Catégorie :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-4 text-secondary">${categorie.libelle}</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Meilleur offre :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-2 text-secondary">${enchere.montantEnchere}</div>
					 <div class="col-sm-1"><h6 class="mb-0">par </h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-2 text-secondary">${enchere.utilisateur.pseudo}</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Mise à prix :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-2 text-secondary">${article.prixInitial}</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Fin de l'enchère :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-2 text-secondary">${article.dateFin}</div>
				</div>
				
				<div style="margin-bottom: 3px; padding: 5px; border: 2px solid black;" class="col-sm-8"><h4 class="mb-0">Retrait :</h4>
				<div style="margin-top: 10px; display: inline-block width: 200px" class="row">
					<div class="col-sm-3"><h6 class="mb-0">Rue :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-6 text-secondary">${article.rue}</div>
				</div>
				<div class="row">
					<div class="col-sm-3"><h6 class="mb-0">Code postal :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-6 text-secondary">${article.codePostal}</div>
				</div>
				<div class="row">
					<div class="col-sm-3"><h6 class="mb-0">Ville :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-6 text-secondary">${article.ville}</div>
				</div>
				</div>
				<div class="row">
					<div class="col-sm-2"><h6 class="mb-0">Vendeur :</h6></div>
					 <div style="margin-bottom: 3px; border: 1px solid black;" class="col-sm-4 text-secondary">${article.utilisateur.pseudo}</div>
				</div>
				<c:choose>
					<c:when test="${user.idUtilisateur == article.utilisateur.idUtilisateur }">
						<div class="form group-row">
							<button name="action" class="btn btn-primary" type="submit" value="modifier">Modifier l'article</button>
							<button name="action" class="btn btn-primary" type="submit" value="supprimer">Annuler la vente</button>
						</div>
					</c:when>
					<c:otherwise>
						<div class="form group-row">
							<input name="article" type="hidden" value="${article.idArticle}"> <label for="encherir" class="col-form-label">Ma proposition :</label>
							<c:choose>
								<c:when test="${enchere.montantEnchere <= article.prixInitial }">
									<input name="encherir" type="number" class="col-2 col-form-control" id="encherir" min="${article.prixInitial + 1}" value="${article.prixInitial + 1}">
								</c:when>
								<c:otherwise>
									<input name="encherir" type="number" class="col-2 col-form-control" id="encherir" min="${enchere.montantEnchere + 1}" value="${enchere.montantEnchere + 1}">
								</c:otherwise>
							</c:choose>
							<button name="action" class="btn btn-primary" type="submit" value="encherir">Enchérir</button>
						</div>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</div>
	<c:if test="${!empty listeCodesErreurs }">
		<c:forEach var="error" items="${listeCodesErreurs }">
			<p class="error">
				<c:out value="${LecteurMessages.getMessageErreur(error) }"></c:out>
			</p>
		</c:forEach>
	</c:if>
	<%@ include file="../inclusions/footer.jspf"%>
</body>
</html>