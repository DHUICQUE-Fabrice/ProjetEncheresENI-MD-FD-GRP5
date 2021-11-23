<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvel utilisateur</title>
</head>
<body>
	<form method="post" action="createAccount">
		<label for="pseudo">Pseudo : </label> 
		<input type="text" name="pseudo" id="pseudo">
		<br> 
		<label for="nom">Nom : </label> 
		<input type="text" name="nom" id="nom">
		<br> 
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" id="prenom">
		<br>
		<label for="email">Email : </label>
		<input type="text" name="email"	id="email">
		<br>
		<label for="telephone">Téléphone : </label>
		<input type="text" name="telephone" id="telephone">
		<br>
		<label for="rue">Rue : </label>
		<input type="text" name="rue" id="rue">
		<br>
		<label for="codePostal">Code Postal : </label>
		<input type="text" name="codePostal" id="codePostal">
		<br>
		<label for="ville">Ville : </label>
		<input type="text" name="ville"	id="ville">
		<br>
		<label for="motDePasse">Mot de Passe : </label>
		<input type="password" name="motDePasse" id="motDePasse">
		<br>
		<label for="confirmation">Confirmation : </label>
		<input type="password" name="confirmation" id="confirmation">
		<br>
		<input type="submit" value="valider">
	</form>

</body>
</html>