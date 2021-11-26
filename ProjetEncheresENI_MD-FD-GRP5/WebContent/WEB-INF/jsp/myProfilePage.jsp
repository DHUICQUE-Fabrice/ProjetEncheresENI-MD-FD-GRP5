<%@page import="fr.eni.encheres.bo.Utilisateur"%>
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
<title>Mon profil</title>
</head>
<body class="d-flex flex-column h-100">
	<%@ include file="../inclusions/header.jspf"%>
	<%
		Utilisateur user = (Utilisateur) session.getAttribute("user");
	%>
	<div class="col-md-8">
		<div class="card mb-3">
			<div class="card-body">
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Pseudo :</h6>
					</div>
					<div class="col-sm-9 text-secondary"><%=user.getPseudo()%></div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Nom et prénom :</h6>
					</div>
					<div class="col-sm-9 text-secondary"><%=user.getNom() %> <%=user.getPrenom() %></div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Email :</h6>
					</div>
					<div class="col-sm-9 text-secondary"><%=user.getEmail() %></div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Téléphone</h6>
					</div>
					<div class="col-sm-9 text-secondary"><%=user.getTelephone() %></div>
				</div>
				<hr>
								<div class="row">
					<div class="col-sm-3">
						<h6 class="mb-0">Adresse :</h6>
					</div>
					<div class="col-sm-9 text-secondary"><%=user.getRue() %> <%=user.getCodePostal() %> <%=user.getVille() %></div>
				</div>
				<hr>
				<div class="row">
					<div class="col-sm-3">
						<a class="btn btn-info " href="editProfile">Modifier</a>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-info " href="encheres">Accueil</a>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-info " href="supprimer">Supprimer</a>
					</div>
				</div>
			</div>
		</div>
		</div>
		

		<%@ include file="../inclusions/footer.jspf"%>
</body>
</html>