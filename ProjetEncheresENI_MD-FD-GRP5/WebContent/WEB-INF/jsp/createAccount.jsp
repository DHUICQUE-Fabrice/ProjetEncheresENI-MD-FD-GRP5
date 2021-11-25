<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.LecteurMessages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvel utilisateur</title>
</head>
<body>
<%@ include file="../inclusions/header.jspf" %>

	<div class="container-fluid">
		<h1>Formulaire d'inscription</h1>
		<br>
		<form method="post" action="createAccount">
			<div class="row">
				<div class="form-floating col-4">
					<input type="text" name="pseudo" id="pseudo" value="${pseudo }"
						class="form-control" placeholder="pseudo"><label
						for="pseudo">Pseudo : </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="nom" id="nom" value="${nom }"
						class="form-control" placeholder="nom"> <label for="nom">Nom
						: </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="prenom" id="prenom" value="${prenom }"
						class="form-control" placeholder="prenom"> <label
						for="prenom">Prénom : </label>

				</div>




			</div>
			<br>
			<br>
			<div class="row">
				<div class="form-floating col-6">
					<input type="text" name="email" id="email" value="${email }"
						class="form-control" placeholder="email"> <label
						for="email">Email : </label>

				</div>
				<div class="form-floating col-6">
					<input type="text" name="telephone" id="telephone"
						value="${telephone }" class="form-control" placeholder="telephone">
					<label for="telephone">Téléphone : </label>

				</div>

			</div>
			<br>
			<br>

			<div class="row">
				<div class="form-floating col-4">
					<input type="text" name="rue" id="rue" value="${rue }"
						class="form-control" placeholder="rue"> <label for="rue">Rue
						: </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="codePostal" id="codePostal"
						value="${codePostal }" class="form-control"
						placeholder="codePostal"> <label for="codePostal">Code
						Postal : </label>

				</div>
				<div class="form-floating col-4">
					<input type="text" name="ville" id="ville" value="${ ville}"
						class="form-control" placeholder="ville"> <label
						for="ville">Ville : </label>

				</div>

			</div>
			<br>
			<br>

			<div class="row">
				<div class="form-floating col-6">
					<input type="password" name="password" id="password"
						value="${password }" class="form-control" placeholder="password">
					<label for="password">Mot de Passe : </label>
				</div>
				<div class="form-floating col-6">
					<input type="password" name="confirmation" id="confirmation"
						value="${confirmation }" class="form-control"
						placeholder="confirmation"> <label for="confirmation">Confirmation
						: </label>
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


	<%
		List<Integer> listeCodesErreur = (ArrayList<Integer>) request.getAttribute("listeCodesErreurs");
		if (listeCodesErreur != null) {
			for (int error : listeCodesErreur) {
	%>
	<p class="error"><%=LecteurMessages.getMessageErreur(error)%></p>
	<%
		}
		}
	%>
</body>
</html>