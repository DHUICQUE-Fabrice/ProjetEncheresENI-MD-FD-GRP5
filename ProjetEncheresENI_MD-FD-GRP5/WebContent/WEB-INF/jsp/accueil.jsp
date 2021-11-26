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
<body class="d-flex flex-column h-100">
<%@ include file="../inclusions/header.jspf" %>

<main class="flex-shrink-0">
<div class="container">
<%
		if (session.getAttribute("user") != null) {
	%>
		<a class="nav-link" href="article">Vendre un article</a>
		
			<%
		} else {
	%>
        	<%
		}
	%>
</div>

</main>	
<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>