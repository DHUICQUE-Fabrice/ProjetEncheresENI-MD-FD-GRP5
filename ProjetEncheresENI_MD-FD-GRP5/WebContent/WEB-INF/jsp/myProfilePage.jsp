<%@page import="fr.eni.encheres.bo.Utilisateur"%>
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
<title>Mon profil</title>
</head>
<body class="d-flex flex-column h-100">
	<%@ include file="../inclusions/header.jspf"%>

	<div class="col-md-8">
		<div class="card mb-3">
			<div class="card-body">
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Pseudo :</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.pseudo }</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Nom et prénom :</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.nom }
						${user.prenom }</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Email :</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.email }</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Téléphone</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.telephone }</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Adresse :</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.rue }
						${user.codePostal } ${user.ville }</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Crédit :</h6>
					</div>
					<div class="col-sm-9 text-secondary">${user.credit } points</div>
				</div>
				<hr>
				<div class="row">

					<div class="col-sm-3">
						<a class="btn btn-info " href="editProfile">Modifier</a>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-info " href="encheres">Accueil</a>
					</div>
					<form action="supprimer" method="post" class="col-sm-3">
						<div>
							<input type="hidden" value=supprimer><input type="submit"
								value="Supprimer">
						</div>
					</form>
					<c:if test="${!empty demandeSuppression}">
						<form action="suppressionConfirmee" method="post">
							<div class="col-sm-3 form-floating">
								<input type="password" name="password" value=""
									class="form-control" placeholder="password"><label
									for="password">Mot de passe : </label>

							</div>
							<div class="col-sm-3">
								<input type="submit"
									value="Confirmer
									la suppression de profil">
							</div>
						</form>
					</c:if>
					<c:if test="${!empty wrongPass }">
						<p class="error">Mot de passe erroné</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="../inclusions/footer.jspf"%>
</body>
</html>