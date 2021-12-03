package fr.eni.encheres.view;

import java.io.IOException;
import java.time.LocalDate;
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
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("action") == null) {
			doGet(request, response);
			return;
		}
		
		if (request.getParameter("action").equals("modifier")) {
			modifierArticle(request, response);
		}else if (request.getParameter("action").equals("supprimer")) {
			deleteArticle(request, response);
			
		}else if (request.getParameter("action").equals("encherir")) {
			encherir(request, response);
		}
	}
	
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArticleManager art = new ArticleManager();
		Article article = art.selectByIdArticle(Integer.parseInt(request.getParameter("article")));
		
		
		int id = article.getIdArticle();
		try {
			art.deleteArticle(id);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
			doGet(request, response);
		}
	}
	
	public void encherir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Encherir sur un article d'un utilisateur par un acheteur.
		Enchere enchere = new Enchere();
		EnchereManager ench = new EnchereManager();
		ArticleManager art = new ArticleManager();
		HttpSession session = request.getSession();
					
		Article article = art.selectByIdArticle(Integer.parseInt(request.getParameter("article")));
		request.setAttribute("article", article);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
		enchere.setArticle(article);
		enchere.setUtilisateur(utilisateur);
		enchere.setDateEnchere(LocalDate.now());
		enchere.setMontantEnchere(Integer.parseInt(request.getParameter("encherir")));
		try {
			ench.ajouter(enchere);
			doGet(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
			doGet(request, response);
		}
	}
	
	private void modifierArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// modification d'un article par le vendeur.
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		Article article = art.selectByIdArticle(Integer.parseInt(request.getParameter("article")));
		List<Categorie> categorie = cat.selectAll();
		request.setAttribute("article", article);
		request.setAttribute("categorie", categorie);
		System.out.println(article);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/modifierArticle.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
