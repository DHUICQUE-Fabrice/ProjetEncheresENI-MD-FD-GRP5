<%@page import="fr.eni.encheres.bo.Utilisateur"%>

<%@page import="fr.eni.encheres.bo.Article, java.time.LocalDate"%>
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
<title>Nouvel utilisateur</title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="../inclusions/header.jspf" %>
<main class="flex-shrink-0">

	<div class="container-fluid">
		<h1>Modifier mon profil</h1>
		<br>
		<form method="post" action="editProfile">
			<div class="row">
				<div class="form-floating col-4">
					<input type="text" name="pseudo" id="pseudo" value="${user.pseudo }"
						class="form-control" placeholder="pseudo" required><label
						for="pseudo">Pseudo : </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="nom" id="nom" value="${user.nom }"
						class="form-control" placeholder="nom" required> <label for="nom">Nom
						: </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="prenom" id="prenom" value="${user.prenom }"
						class="form-control" placeholder="prenom" required> <label
						for="prenom">Prénom : </label>
				</div>
			</div>
			<br>
			<br>
			<div class="row">
				<div class="form-floating col-6">
					<input type="text" name="email" id="email" value="${user.email }"
						class="form-control" placeholder="email" required> <label
						for="email">Email : </label>
				</div>
				<div class="form-floating col-6">
					<input type="text" name="telephone" id="telephone"
						value="${user.telephone}" class="form-control" placeholder="telephone">
					<label for="telephone">Téléphone : </label>
				</div>
			</div>
			<br>
			<br>
			<div class="row">
				<div class="form-floating col-4">
					<input type="text" name="rue" id="rue" value="${user.rue}"
						class="form-control" placeholder="rue" required> <label for="rue">Rue
						: </label>
				</div>
				<div class="form-floating col-4">
					<input type="text" name="codePostal" id="codePostal"
						value="${user.codePostal}" class="form-control"
						placeholder="codePostal" required> <label for="codePostal">Code
						Postal : </label>
				</div>
				<div class="form-floating col-4">
					<input type="text" name="ville" id="ville" value="${user.ville}"
						class="form-control" placeholder="ville" required> <label
						for="ville">Ville : </label>
				</div>
			</div>
			<br>
			<br>

			<div class="row">
				<div class="form-floating col-4">
					<input type="password" name="oldPassword" id="oldPassword"
						value="" class="form-control" placeholder="oldPassword" required>
					<label for="oldPassword">Mot de Passe actuel : </label>
				</div>				
				<div class="form-floating col-4">
					<input type="password" name="password" id="password"
						value="" class="form-control" placeholder="password">
					<label for="password">Nouveau Mot de Passe : </label>
				</div>
				<div class="form-floating col-4">
					<input type="password" name="confirmation" id="confirmation"
						value="" class="form-control"
						placeholder="confirmation"> <label for="confirmation">Confirmation du nouveau Mot De Passe : </label>
				</div>

			</div>
			<br>
			<input type="submit" value="Valider" class="btn-primary col-3">



		</form>
		<form method="get" action="encheres">
			<br>
			<input type="submit" value="Annuler" class="btn-secondary col-3">
		</form>
	</div>


	<c:if test="${!empty listeCodesErreurs }">
		<c:forEach var="error" items="${listeCodesErreurs }">
			<p class="error"><c:out value="${LecteurMessages.getMessageErreur(error) }"></c:out></p>
		</c:forEach>
	
	</c:if>
		<c:if test="${!empty wrongPass }">
		<br>
		<p class="error">Veuillez rentrer votre mot de passe actuel !</p>
	</c:if>
	
	
	</main>
	<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>