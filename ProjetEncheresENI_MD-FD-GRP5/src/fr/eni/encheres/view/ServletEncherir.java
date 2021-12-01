package fr.eni.encheres.view;

import java.io.IOException;
import java.time.LocalDate;

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
		
		int id = Integer.parseInt(request.getParameter("articleNumber"));
		ArticleManager art = new ArticleManager();
		CategorieManager cat = new CategorieManager();
		EnchereManager enc = new EnchereManager();
		
		Enchere enchere = enc.selectMaxMontantByIdArticle(id);
		
		Article article = art.selectByIdArticle(id);
		Categorie categorie = cat.selectById(article.getCategorie().getIdCategorie());
		
		request.setAttribute("article", article);
		request.setAttribute("categorie", categorie);
		request.setAttribute("enchere", enchere);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/encherir.jsp").forward(request, response);
		
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
				enchere.setArticle(art.selectByIdArticle(Integer.parseInt(request.getParameter("article"))));
				enchere.setUtilisateur((Utilisateur) session.getAttribute("user"));
				enchere.setDateEnchere(LocalDate.now());
				enchere.setMontantEnchere(Integer.parseInt(request.getParameter("encherir")));
				
				ench.ajouter(enchere);
				
				response.sendRedirect("encheres");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
