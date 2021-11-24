<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvel utilisateur</title>
</head>
<body>
	<form method="post" action="createAccount">
		<label for="pseudo">Pseudo : </label> 
		<input type="text" name="pseudo" id="pseudo" value="${pseudo }">
		<br> 
		<label for="nom">Nom : </label> 
		<input type="text" name="nom" id="nom" value="${nom }">
		<br> 
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" id="prenom" value="${prenom }">
		<br>
		<label for="email">Email : </label>
		<input type="text" name="email"	id="email" value="${email }">
		<br>
		<label for="telephone">Téléphone : </label>
		<input type="text" name="telephone" id="telephone" value="${telephone }">
		<br>
		<label for="rue">Rue : </label>
		<input type="text" name="rue" id="rue" value="${rue }">
		<br>
		<label for="codePostal">Code Postal : </label>
		<input type="text" name="codePostal" id="codePostal" value="${codePostal }">
		<br>
		<label for="ville">Ville : </label>
		<input type="text" name="ville"	id="ville" value="${ ville}">
		<br>
		<label for="password">Mot de Passe : </label>
		<input type="password" name="password" id="password" value="${password }">
		<br>
		<label for="confirmation">Confirmation : </label>
		<input type="password" name="confirmation" id="confirmation" value="${confirmation }">
		<br>
		<input type="submit" value="valider">
	</form>
	<form method="get" action="encheres">
		<input type="submit" value="Annuler">
	</form>
	
	<c:if test="${!empty wrongConfirmation }">
		<p style="font-weight:bold;color:red">Confirmation du mot de passe erronée</p>
	</c:if>
	<c:if test="${!empty pseudoExists }">
		<p style="font-weight:bold;color:red">Ce pseudo existe déjà !</p>	
	</c:if>
	<c:if test="${!empty emailExists }">
		<p style="font-weight:bold;color:red">Cet email est déjà associé à un compte existant !</p>	
	</c:if>
</body>
</html>