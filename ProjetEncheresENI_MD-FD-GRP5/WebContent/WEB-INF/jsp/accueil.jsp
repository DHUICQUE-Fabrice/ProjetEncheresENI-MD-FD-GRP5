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
<title>Accueil</title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="../inclusions/header.jspf" %>

<main class="flex-shrink-0">
<div class="container">
<c:forEach var="article" items="${articles }">
<p>${article }</p>
</c:forEach>
</div>




</main>	
<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>