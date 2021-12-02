package fr.eni.encheres.view;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Article article;
		int id;
		ArticleManager art = new ArticleManager();
		if (request.getParameter("articleNumber") != null) {
			id = Integer.parseInt(request.getParameter("articleNumber"));
			article = art.selectByIdArticle(id);
		} else {
			article = (Article) request.getAttribute("article");
			id = article.getIdArticle();
		}
		
		CategorieManager cat = new CategorieManager();
		EnchereManager enc = new EnchereManager();
		
		Enchere enchere = enc.selectMaxMontantByIdArticle(id);
		
		Categorie categorie = cat.selectById(article.getCategorie().getIdCategorie());
		
		request.setAttribute("article", article);
		request.setAttribute("categorie", categorie);
		request.setAttribute("enchere", enchere);
		
		request.setAttribute("test", "erreur");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/encherir.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("encherir") == null) {
			doGet(request, response);
		} else {
			
			Enchere enchere = new Enchere();
			EnchereManager ench = new EnchereManager();
			ArticleManager art = new ArticleManager();
			HttpSession session = request.getSession();
			
			try {
				Article article = art.selectByIdArticle(Integer.parseInt(request.getParameter("article")));
				request.setAttribute("article", article);
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
				enchere.setArticle(article);
				enchere.setUtilisateur(utilisateur);
				enchere.setDateEnchere(LocalDate.now());
				enchere.setMontantEnchere(Integer.parseInt(request.getParameter("encherir")));
				ench.ajouter(enchere);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/encherir.jsp");
				requestDispatcher.forward(request, response);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
				doGet(request, response);
			}
			
		}
	}
	
}
