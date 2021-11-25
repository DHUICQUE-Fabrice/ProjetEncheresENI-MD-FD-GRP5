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
<title>Accueil</title>
</head>
<body>
	<%
		if (session.getAttribute("user") != null) {
	%>
	<form method="get" action="encheres">
		<input type="hidden" name="disconnect" id="disconnect"
			value="disconnect"> <input type="submit" value="Se déconnecter"><br>
	</form>
	<%
		} else {
	%>
	<a href="createAccount">S'inscrire</a>
	<br>
	<a href="login">Se connecter</a>
	<br>
	<%
		}
	%>

</body>
</html>