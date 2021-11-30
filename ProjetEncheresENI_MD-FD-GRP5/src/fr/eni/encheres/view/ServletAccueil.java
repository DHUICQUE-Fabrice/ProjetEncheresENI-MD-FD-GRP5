package fr.eni.encheres.view;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/encheres")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArticleManager articleManager = new ArticleManager();
		List<Article> articles = articleManager.allArticle();
		request.setAttribute("articles", articles);
		CategorieManager categorieManager = new CategorieManager();
		List<Categorie> categories = categorieManager.selectAll();
		request.setAttribute("categories", categories);
		if (request.getParameter("disconnect") != null && request.getParameter("disconnect").equals("disconnect")) {
			session.invalidate();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
