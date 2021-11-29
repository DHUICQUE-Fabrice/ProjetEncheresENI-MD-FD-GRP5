package fr.eni.encheres.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/article")
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategorieManager cat = new CategorieManager();
		request.setAttribute("categorie", cat.selectAll());
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ajouterArticle.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("ajouter")) {
			ajouterArticle(request, response);
			
		}else if (action.equals("delete")){
			deleteArticle(request, response);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp");
		requestDispatcher.forward(request, response);
		
	}

	private void ajouterArticle(HttpServletRequest request, HttpServletResponse response) {
		Article article = new Article();
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		HttpSession session = request.getSession();
		
		article.setNomArticle(request.getParameter("nomArticle"));
		article.setDescription(request.getParameter("description"));
		article.setCategorie(cat.selectById(Integer.parseInt(request.getParameter("categorie"))));
		article.setUrlImage(request.getParameter("image"));
		article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
		article.setDateDebut(LocalDate.parse(request.getParameter("debutEnchere")));
		article.setDateFin(LocalDate.parse(request.getParameter("finEnchere")));
		article.setRue(request.getParameter("rue"));
		article.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));
		article.setVille(request.getParameter("ville"));
		article.setUtilisateur((Utilisateur) session.getAttribute("user"));
		
		System.out.println(article.toString());
		try {
			art.ajouter(article);
		} catch (BusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void modifierArticle(HttpServletRequest request, HttpServletResponse response) {
		Article article = new Article();
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		HttpSession session = request.getSession();
		
		
		try {
			
			article.setNomArticle(request.getParameter("nomArticle"));
			article.setDescription(request.getParameter("description"));
			article.setCategorie(cat.selectById(Integer.valueOf(request.getParameter("categorie"))));
			article.setUrlImage(request.getParameter("image"));
			article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			article.setDateDebut(LocalDate.parse(request.getParameter("debutEnchere")));
			article.setDateFin(LocalDate.parse(request.getParameter("finEnchere")));
			article.setRue(request.getParameter("rue"));
			article.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));
			article.setVille(request.getParameter("ville"));
			article.setUtilisateur((Utilisateur) session.getAttribute("user"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response) {
		ArticleManager art = new ArticleManager();
		
		int id = Integer.parseInt(request.getParameter("idArticle"));
		try {
			art.deleteArticle(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
