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
<title>Article a vendre.</title>
</head>
<body>
<%@ include file="../inclusions/header.jspf" %>
<div class="row">
	<div class="col-md-3">
		<div class="col-md-3 themed-grid-col">
			<img src="/image_default.jpg" alt="image de l'article vendu." width="200" height="200"/>
		</div>
	</div>
	<div class="col-md-6">
		<form class="row g-3">
           	<div class="col-sm-6">
        		<label for="article" class="form-label">Article :</label>
				<input name="nomArticle" type="text" class="form-control" id="article" placeholder="Nom de l'article" value="" required>
				<div class="invalid-feedback">
					Le nom de l'articlt <strong>doit être renseigné</strong>.
				</div>
            </div>

			<div class="mb-3">
   				<label for="description" class="form-label">Descrption :</label>
				<textarea name="description" class="form-control is-invalid" id="description" placeholder="Description de l'article vendu." required>Description de l'article vendu.</textarea>
				<div class="invalid-feedback">
					La description de l'articlt <strong>doit être renseignée</strong>.
				</div>
			</div>

			<div class="col-md-5">
				<label for="country" class="form-label">Catégories :</label>
				<select name="categorie" class="form-select"  id="country" required>
             		<option value="">Choose...</option>
                	<option>Livre</option>
              	</select>
				<div class="invalid-feedback">
					Vous devez choisir une Catégories pour votre article.
				</div>
            </div>

			<div class="mb-3">
				<label class="form-label">Photo de l'article :</label>
				<input name="image" type="file" class="form-control"  aria-label="Upload image">
			</div>
			
			<div class="mb-3">
				<label class="form-label">Prix Initial :</label>
				<input name="prixInitial" id="prixInitial" type="number"  value="" placeholder="Prix Initial">
			</div>
			
			<div class="mb-3">
   				<label for="dateDebut" class="form-label">Début de l'enchère :</label>
				<input name="debutEnchère" type="date" class="form-control" id="dateDebut" placeholder="date début enchère" value="" required>
				<div class="invalid-feedback">
					La date de début de l'enchère de l'articlt <strong>doit être renseignée</strong>.
				</div>
			</div>
			
			<div class="mb-3">
   				<label for="dateFin" class="form-label">Fin de l'enchère :</label>
				<input name="debutEnchère" type="date" class="form-control" id="dateFin" placeholder="date fin enchère" value="" required>
				<div class="invalid-feedback">
					La date de fin de l'enchère de l'articlt <strong>doit être renseignée</strong>.
				</div>
			</div>
         
         	<fieldset class="col-6">
         		<legend>Retrait:</legend>
 				<label for="rue" class="form-label">Rue :</label>
  				<input type="text" id="rue" name="rue"><br><br>
 				<label for="codePostal" class="form-label">Code postal :</label>
 				<input type="number" id="codePostal" name="codePostal"><br><br>
  				<label for="ville" class="form-label">Ville :</label>
  				<input type="text" id="ville" name="ville"><br><br>
         	</fieldset>
         
			<div class="col-6">
 				<button class="btn btn-primary" type="submit">Enregistrer</button>
 				<button class="btn btn-primary" type="submit">Annuler</button>
 				<button class="btn btn-primary" type="submit">Annuler la vente</button>
			</div>
		</form>
	</div>
	<div class="col-md-3">
	</div>
</div>
</body>
</html>