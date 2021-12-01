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
<title>ENI Enchères.</title>
</head>
<body class="container-fluid">
	<%@ include file="../inclusions/header.jspf"%>
	<h2>Détails de la vente.</h2>


	<div class="row">
		<nav id="sideMenu" class="col-12 col-sm-4 col-md-4">
			<img src="img/image_default.jpg" width="200" height="200" />
		</nav>

		<main id="grilleChoix" class="col-12 col-sm-8 col-md-8">
		<form method="post" action="<%=request.getContextPath()%>/encherir">

			<div class="form group-row">
				<label for="article" class="col-form-label">Article :</label> <input
					name="nomArticle" type="text" class="col-8 col-form-control"
					id="article" value="${article.nomArticle}" readonly>
			</div>

			<div class="form group-row">
				<label for="description" class="col-form-label">Descrption :</label>
				<textarea name="description" class="col-8 col-form-control"
					id="description" readonly><c:out
						value="${article.description}" /> </textarea>
			</div>

			<div class="form group-row">
				<label for="categorie" class="col-form-label">Catégories :</label> <input
					name="categorie" type="text" class="col-6 col-form-control"
					id="categorie" value="${categorie.libelle}" readonly>
			</div>

			<div class="form group-row">
				<label for="offre" class="col-form-label">Meilleur offres :</label>
				<input name="offre" type="text" class="col-1 col-form-control"
					id="offre" value="${enchere.montantEnchere}" readonly> <label
					for="acheteur" class="col-form-label"> par </label> <input
					name="acheteur" type="text" class="col-4 col-form-control"
					id="acheteur" value="${enchere.utilisateur.pseudo}" readonly>
			</div>

			<div class="form group-row">
				<label for="miseAPrix" class="col-form-label">Mise a prix :</label>
				<input name="miseAPrix" type="text" class="col-2 col-form-control"
					id="miseAPrix" value="${article.prixInitial}" readonly>
			</div>

			<div class="form group-row">
				<label for="finEnchere" class="col-form-label">Fin de
					l'enchère :</label> <input name="finEnchere" type="text"
					class="col-4 col-form-control" id="finEnchere"
					value="${article.dateFin}" readonly>
			</div>

			<label class="form-label">Retrait :</label>
			<div class="form group-row">
				<label for="rue" class="col-form-label">Rue :</label> <input
					name="rue" type="text" class="col-8 col-form-control" id="rue"
					value="${article.rue}" readonly>
			</div>

			<div class="form group-row">
				<label for="codePostal" class="col-form-label">Code postal :</label>
				<input name="codePostal" type="text" class="col-4 col-form-control"
					id="codePostal" value="${article.codePostal}" readonly>
			</div>

			<div class="form group-row">
				<label for="ville" class="col-form-label">Ville :</label> <input
					name="ville" type="text" class="col-8 col-form-control" id="ville"
					value="${article.ville}" readonly>
			</div>

			<div class="form group-row">
				<label for="vendeur" class="col-form-label">Vendeur :</label> <input
					name="vendeur" type="text" class="col-4 col-form-control"
					id="vendeur" value="${article.utilisateur.pseudo}" readonly>
			</div>

			<div class="form group-row">
				<input name="article" type="hidden" value="${article.idArticle}">
				<label for="encherir" class="col-form-label">Ma proposition
					:</label> <input name="encherir" type="number"
					class="col-2 col-form-control" id="encherir"
					min="${enchere.montantEnchere + 1}"
					value="${enchere.montantEnchere + 1}">
				<button name="encherir" class="btn btn-primary" type="submit">Enchérir</button>
			</div>
		</form>
		<c:if test="${!empty requestScope.listeCodesErreurs }">
			<c:forEach var="error" items="${requestScope.listeCodesErreurs }">
				<p class="error">
					<c:out value="${LecteurMessages.getMessageErreur(error) }"></c:out>
				</p>
			</c:forEach>
		</c:if> </main>
	</div>
	<%@ include file="../inclusions/footer.jspf"%>

</body>
</html>