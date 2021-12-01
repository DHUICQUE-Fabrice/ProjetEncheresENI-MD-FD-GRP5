package fr.eni.encheres.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailUtilisateur
 */
@WebServlet("/detailUtilisateur")
public class ServletDetailUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/detailUtilisateur.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		utilisateur = utilisateurManager.getUserById(Integer.parseInt(request.getParameter("userNumber")));
		ArticleManager articleManager = new ArticleManager();
		List<Article> articles = articleManager.allArticlesByUser(utilisateur.getIdUtilisateur());
		request.setAttribute("user", utilisateur);
		request.setAttribute("articles", articles);
		doGet(request, response);
	}
	
}
