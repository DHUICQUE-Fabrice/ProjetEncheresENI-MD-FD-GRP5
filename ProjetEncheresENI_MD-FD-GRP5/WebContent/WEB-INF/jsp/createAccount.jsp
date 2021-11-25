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
	
	
	<%
	List<Integer> listeCodesErreur = (ArrayList<Integer>)request.getAttribute("listeCodesErreurs");
	if(listeCodesErreur != null){
		for(int error:listeCodesErreur){
			%>
			<p class="error"><%=LecteurMessages.getMessageErreur(error) %></p>
			<%
		}
	}
	%>
</body>
</html>