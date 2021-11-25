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
<title>Se connecter</title>
</head>
<body>
	<h1>ENI-Enchères</h1> <!-- A mettre dans un header.html pour faire un "include" -->
	<form method="post" action="login">
		<label for="userName">Identifiant : </label><input type="text" name="userName" id="userName" value="${userName }">
		<label for="userPassword">Mot de passe : </label><input type="password" name="userPassword" id="userPassword" value="${userPassword }">
		<input type="submit" value="Connexion">	
		<label for="rememberMe">Se souvenir de moi</label><input type="checkbox" id="rememberMe" value="rememberMe" name="rememberMe">
		<!-- Créer gestion du cookie pour le rememberMe -->
	</form>
		<a href="forgottenPassword">Mot de passe oublié</a><!-- Créer page + servlet forgottenPassword -->
		<a href="createAccount"><button>Créer un compte</button></a><!-- Créer une page + servlet createAccount -->
		<c:if test="${!empty unknown }">
			<br>
			<p class="error">Utilisateur inconnu !</p>
		</c:if>
		<c:if test="${!empty wrongPass }">
			<br>
			<p class="error">Mot de passe incorrect !</p>
		</c:if>
</body>
</html>