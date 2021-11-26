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
<title>ENI Enchères.</title>
</head>
<body class="container-fluid">
<%@ include file="../inclusions/header.jspf" %>
<h2>Article a vendre.</h2>
	
 
<div class="row">
	<nav id="sideMenu" class="col-12 col-sm-3 col-md-2">
		<img src="img/image_default.jpg"  class="img-fluid" alt="image de l'article vendu." width="200" height="200"/>
    </nav>
     
	<main id="grilleChoix" class="col-12 col-sm-9 col-md-10">
	<form>
           	<div>
        		<label for="article" class="form-label">Article :</label>
				<input name="nomArticle" type="text" class="form-control" id="article" placeholder="Nom de l'article" value="" required>
            </div>

			<div>
   				<label for="description" class="form-label">Descrption :</label>
				<textarea name="description" class="form-control is-invalid" id="description" placeholder="Description de l'article vendu." required>Description de l'article vendu.</textarea>
			</div>

			<div>
				<label for="country" class="form-label">Catégories :</label>
				<select name="categorie" class="form-select"  id="country" required>
             		<option value="">Choose...</option>
                	<option>Livre</option>
              	</select>
            </div>

			<div>
				<label class="form-label">Photo de l'article :</label>
				<input name="image" type="file" class="form-control"  aria-label="Upload image">
			</div>
			
			<div>
				<label class="form-label">Prix Initial :</label>
				<input name="prixInitial" id="prixInitial" type="number"  value="" placeholder="Prix Initial">
			</div>
			
			<div>
   				<label for="dateDebut" class="form-label">Début de l'enchère :</label>
				<input name="debutEnchère" type="date" class="form-control" id="dateDebut" placeholder="date début enchère" value="" required>
			</div>
			
			<div>
   				<label for="dateFin" class="form-label">Fin de l'enchère :</label>
				<input name="debutEnchère" type="date" class="form-control" id="dateFin" placeholder="date fin enchère" value="" required>
			</div>
         
         	<fieldset>
         		<legend>Retrait:</legend>
 				<label for="rue" class="form-label">Rue :</label>
  				<input type="text" id="rue" name="rue"><br><br>
 				<label for="codePostal" class="form-label">Code postal :</label>
 				<input type="number" id="codePostal" name="codePostal"><br><br>
  				<label for="ville" class="form-label">Ville :</label>
  				<input type="text" id="ville" name="ville"><br><br>
         	</fieldset>
         
			<div >
 				<button class="btn btn-primary" type="submit">Enregistrer</button>
 				<button class="btn btn-primary" type="submit">Annuler</button>
 				<button class="btn btn-primary" type="submit">Annuler la vente</button>
			</div>
		</form>
	
	</main>
</div>
<%@ include file="../inclusions/footer.jspf" %>
 <script src="bootstrap-5.1.1-dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>