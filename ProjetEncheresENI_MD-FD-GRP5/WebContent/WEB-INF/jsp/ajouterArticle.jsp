<%@page import="fr.eni.encheres.bo.Article"%>
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
<title>ENI Enchères.</title>
</head>
<body class="container-fluid">
<%@ include file="../inclusions/header.jspf" %>
<h2>Article a vendre.</h2>

<div class="row">
	<nav id="sideMenu" class="col-12 col-sm-4 col-md-4">
		<img src="img/image_default.jpg"  class="img-fluid" alt="image de l'article vendu." width="200" height="200"/>
    </nav>
     
	<div id="grilleChoix" class="col-12 col-sm-8 col-md-8">
		<form method="post" action="<%=request.getContextPath()%>/article"> 
           	<div class="form group-row">
        		<label for="article" class="col-form-label">Article :</label>
				<input name="nomArticle" type="text" class=" col-8 col-form-control" id="article" placeholder="Nom de l'article" value="" required>
            </div>

			<div class="form group-row">
   				<label for="description" class="col-form-label">Descrption :</label>
				<textarea name="description" class="col-8 col-form-control is-invalid" id="description" placeholder="Description de l'article vendu." required>Description.</textarea>
			</div>

			<div class="form group-row">
				<label for="categorie" class="col-form-label">Catégories :</label>
				<select name="categorie" class="col-8 col-form-select"  id="categorie" required>
             		<c:forEach var="cat" items="${requestScope.categorie}">
             			<option value="${cat.getIdCategorie()}">${cat.getLibelle()}</option>
             		</c:forEach>
              	</select>
            </div>

			<div class="form group-row">
				<label class="col-form-label">Photo de l'article :</label>
				<input name="image" type="file" class="col-8 col-form-control"  aria-label="Upload image">
			</div>
			
			<div class="form group-row">
				<label class="col-form-label">Prix Initial :</label>
				<input name="prixInitial" id="prixInitial" type="number"  value="" placeholder="Prix Initial">
			</div>
			
			<div class="form group-row">
   				<label for="dateDebut" class="col-form-label">Début de l'enchère :</label>
				<input name="debutEnchere" type="date" class="col-4 col-form-control" id="dateDebut" placeholder="date début enchère" value="" required>
			</div>
			
			<div class="form group-row">
   				<label for="dateFin" class="col-form-label">Fin de l'enchère :</label>
				<input name="finEnchere" type="date" class="col-4 col-form-control" id="dateFin" placeholder="date fin enchère" value="" required>
			</div>
         
         	<fieldset class="col-8 form group-row">
         		<legend>Retrait:</legend>
 				<label for="rue" class="col-form-label">Rue :</label>
  				<input name="rue" type="text" id="rue" class="col-8 col-form-control" ><br>
 				<label for="codePostal" class="col-form-label">Code postal :</label>
 				<input name="codePostal" type="number" id="codePostal" class="col-8 col-form-control" ><br>
  				<label for="ville" class="col-form-label">Ville :</label>
  				<input name="ville" type="text" id="ville" class="col-8 col-form-control" ><br>
         	</fieldset>
         	<div class="form group-row">
 				<button name="action" class="btn btn-primary" type="submit" value="ajouter">Enregistrer</button>
 				<button name="action"class="btn btn-primary" type="submit"  value="annuler">Annuler</button>
			</div>
		</form>
	
	</div>
</div>
<%@ include file="../inclusions/footer.jspf" %>
 <script src="bootstrap-5.1.1-dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>