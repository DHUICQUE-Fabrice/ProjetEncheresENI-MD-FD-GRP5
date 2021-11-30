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
<title>Se connecter</title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="../inclusions/header.jspf" %>
<main class="flex-shrink-0">

	<div class="container-fluid">
		<form method="post" action="login" class="">
		<div class="row">
			<div class="form-floating col-6">
				<input type="text" name="userName" id="userName" value=""
					class="form-control" placeholder="Pseudo"> <label
					for="userName">Identifiant : </label>
			</div>
			<div class="form-floating col-6">
				<input type="password" name="userPassword" id="userPassword"
					value="${userPassword }" class="form-control"
					placeholder="password"> <label for="userPassword">Mot
					de passe : </label>
			</div>
			</div>
			
			<div class="row">
				<div class="col-6">
					<input type="submit" value="Connexion" class="btn-primary col-2">
				</div>
				<div class="col-6">

					<input type="checkbox" id="rememberMe" value="rememberMe"
						name="rememberMe" class="form-check-input"> <label
						for="rememberMe" class="col-form-label" class="form-check-label">Se
						souvenir de moi </label>
				</div>
			</div>

			<%
				//TODO Créer gestion du cookie pour le rememberMe
			%>
		</form>
	</div>




	<a href="forgottenPassword"><button class="btn-secondary">Mot
			de passe oublié</button></a>
	<%
		//TODO Créer page + servlet forgottenPassword
	%>
	<a href="createAccount"><button class="btn-secondary">Créer
			un compte</button></a>
	<c:if test="${!empty unknown || !empty wrongPass}">
		<br>
		<p class="error">Couple Login/Mot de passe incorrect !</p>
	</c:if>
	
</main>	
	
<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>