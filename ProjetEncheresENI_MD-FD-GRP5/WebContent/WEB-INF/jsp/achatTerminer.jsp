<%@page import="fr.eni.encheres.bo.Utilisateur"%>

<%@page import="fr.eni.encheres.bo.Article, java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.LecteurMessages"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI Enchères.</title>
</head>
<body class="container-fluid">
	<%@ include file="../inclusions/header.jspf" %>
	<h2>Vous avez remporté l'enchère.</h2>
	
 
<div class="row">
	<nav id="image" class="col-12 col-sm-4 col-md-4">
		<img src="img/image_default.jpg" width="200" height="200"/>
    </nav>
S	<main id="formulaire" class="col-12 col-sm-8 col-md-8">
		<form  method="post" action="<%=request.getContextPath()%>/encherir">
           	<div class="form group-row">
        		<label for="article" class="col-form-label">Article :</label>
				<input name="nomArticle" type="text" class="col-form-control" id="article" readonly>
            </div>

			<div class="form group-row">
   				<label for="description" class="col-form-label">Descrption :</label>
				<textarea name="description" class="col-6 col-form-control" id="description" readonly></textarea>
			</div>

			<div class="form group-row">
				<label for="offre" class="col-form-label">Meilleur offres :</label>
				<input name="offre" type="text" class="col-form-control" id="offre" readonly>
				<label for="offre" class="col-form-label">Pts.</label>
			</div>
			
			<div  class="form group-row">
				<label for="miseAPrix" class="col-form-label">Mise a prix :</label>
				<input name="miseAPrix" type="text" class="col-form-control" id="miseAPrix" readonly>
				<label for="offre" class="col-form-label">Pts.</label>
			</div>
						
       		<label class="form-label">Retrait ______________________________</label>
         	<div  class="form group-row">
 				<label for="rue" class="col-form-label">Rue :</label>
  				<input name="rue" type="text" class=" col-form-control" id="rue" readonly>
         	</div>
         	
         	<div  class="form group-row">
         		<label for="codePostal" class="col-form-label">Code postal :</label>
 				<input name="codePostal" type="text" class="col-form-control" id="codePostal" readonly>
         	</div>

			<div  class="form group-row">
				<label for="ville" class="col-form-label">Ville :</label>
  				<input name="ville" type="text" class="col-form-control" id="ville" readonly>
			</div>
			
			<div  class="form group-row">
				<label for="vendeur" class="col-form-label">Vendeur :</label>
  				<input name="vendeur" type="text" class="col-form-control" id="vendeur" readonly>
			</div>
		
			<div  class="form group-row">
				<label for="telephone" class="col-form-label">Telephone :</label>
  				<input name="telephone" type="text" class=" col-form-control" id="telephone" readonly>
			</div>
			
			<div class="form group-row">
				<button class="btn btn-primary" type="submit">Retour</button>
 				<button class="btn btn-primary" type="submit">Retrait Effectué</button>
 			</div>
		</form>
	</main>
</div>
	<%@ include file="../inclusions/footer.jspf" %>
</body>
</html>