<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ENI-Enchères</h1> <!-- A mettre dans un header.html pour faire un "include" -->
	<form method="post" action="authenticateUser"><!-- Créer Servlet authenticateUser -->
		<label for="userName">Identifiant : </label><input type="text" name="userName" id="userName">
		<label for="userPassword">Mot de passe : </label><input type="password" name="userPassword" id="userPassword">
		<input type="submit" value="Connexion">	
		<label for="rememberMe">Se souvenir de moi</label><input type="checkbox" id="rememberMe" value="rememberMe" name="rememberMe">
		<!-- Créer gestion du cookie pour le rememberMe -->
	</form>
		<a href="forgottenPassword">Mot de passe oublié</a><!-- Créer page + servlet forgottenPassword -->
		<a href="createAccount"><button>Créer un compte</button></a><!-- Créer une page + servlet createAccount -->
	
</body>
</html>