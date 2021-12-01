<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<%@ include file="../inclusions/header.jspf"%>
	<main class="flex-shrink-0">
	<div>
		<br>
		<form method="post" action="encheres" class="form">
			<fieldset class="col-7 form group-row">
				<legend>Filtre :</legend>
				<input name="filtre" type="text" id="filtre" class="col-4 col-form-control"><br> <label for="catSelect">Choix de la catégorie : </label> <select name="categorie" id="catSelect">
					<option value="all">Toutes</option>
					<c:forEach var="cat" items="${categories }">
						<option value="${cat.idCategorie }">${cat.libelle }</option>
					</c:forEach>
				</select>
				<button name="action" class="btn btn-primary" type="submit" value="rechercher">Rechercher</button>
			</fieldset>
			<br>
			<div class="row">
				<div class="col-4">
					<label>Achats</label><input type="radio" id="achat" name="selection" checked />
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="achat" id="encheresOuvertes" checked> <label class="form-check-label">Enchères ouvertes</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="achat" id="encheresEnCours" checked> <label class="form-check-label">Mes enchères en cours</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="achat" id="encheresRemportees"> <label class="form-check-label">Mes enchères Remportées</label>
					</div>
				</div>
				<div class="col-4">
					<label>Mes ventes</label> <input type="radio" id="vente" name="selection" />
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="vente" id="ventesEnCours" disabled> <label class="form-check-label">Mes ventes en cours</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="vente" id="ventesNonDebutees" disabled> <label class="form-check-label">Mes ventes non débutées</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="vente" id="ventesTerminees" disabled> <label class="form-check-label">Mes ventes terminées</label>
					</div>
				</div>
			</div>
		</form>
	</div>
	<br>
	<div class="container">
		<div class="album py-5 bg-light">
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
				<c:forEach var="article" items="${articles }">
					<div class="col">
						<div class="card shadow-sm">
							<img class="bd-placeholder-img card-img-top" src="${article.urlImage }" alt="Image de ${article.nomArticle } (url : ${article.urlImage })">
							<div class="card-body">
								<c:choose>
									<c:when test="${!empty sessionScope.user }">
										<form method="post" action="encherir">
											<input type="hidden" name="articleNumber" value="${article.idArticle }">
											<p>
												Nom de l'article : <input type="submit" class="btn btn-link" value="${article.nomArticle }">
											</p>
										</form>
									</c:when>
									<c:otherwise>
										<p>Nom de l'article : ${article.nomArticle }</p>
									</c:otherwise>
								</c:choose>
								<p>Description de l'article : ${article.description }</p>
								<p>Début de l'enchère : ${article.dateDebut }</p>
								<p>Fin de l'enchère : ${article.dateFin }</p>
								<p>Mise à prix : ${article.prixInitial }</p>
								<c:choose>
									<c:when test="${!empty sessionScope.user }">
										<form method="post" action="detailUtilisateur">
											<input type="hidden" name="userNumber" value="${article.utilisateur.idUtilisateur }">
											<p>
												Vendeur : <input type="submit" class="btn btn-link" value="${article.utilisateur.pseudo }">
											</p>
										</form>
									</c:when>
									<c:otherwise>
										<p>Vendeur : ${article.utilisateur.pseudo }</p>
									</c:otherwise>
								</c:choose>
								<p>Catégorie : ${article.categorie.libelle }</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</main>
	<%@ include file="../inclusions/footer.jspf"%>
	<script>
		var achat = document.getElementById("achat");
		var encheresOuvertes = document.getElementById("encheresOuvertes");
		var encheresEnCours = document.getElementById("encheresEnCours");
		var encheresRemportees = document.getElementById("encheresRemportees");
		var vente = document.getElementById("vente");
		var ventesEnCours = document.getElementById("ventesEnCours");
		var ventesNonDebutees = document.getElementById("ventesNonDebutees");
		var ventesTerminees = document.getElementById("ventesTerminees");

		achat.addEventListener("change", function(event) {
			if (event.target.checked) {
				encheresOuvertes.disabled = false;
				encheresEnCours.disabled = false;
				encheresRemportees.disabled = false;

				ventesEnCours.disabled = true;
				ventesEnCours.checked = false;
				ventesNonDebutees.disabled = true;
				ventesNonDebutees.checked = false;
				ventesTerminees.disabled = true;
				ventesTerminees.checked = false;
			}
		}, false);
		vente.addEventListener("change", function(event) {
			if (event.target.checked) {
				ventesEnCours.disabled = false;
				ventesNonDebutees.disabled = false;
				ventesTerminees.disabled = false;

				encheresOuvertes.disabled = true;
				encheresOuvertes.checked = false;
				encheresEnCours.disabled = true;
				encheresEnCours.checked = false;
				encheresRemportees.disabled = true;
				encheresRemportees.checked = false;
			}
		}, false);
	</script>
</body>
</html>