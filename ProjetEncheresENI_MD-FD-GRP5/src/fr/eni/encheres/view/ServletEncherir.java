package fr.eni.encheres.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

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
		int id = Integer.parseInt(request.getParameter("articleNumber"));
		ArticleManager art = new ArticleManager();
		CategorieManager cat = new CategorieManager();
		EnchereManager enc = new EnchereManager();
		
		List<Enchere> listeEnchereArticle = enc.allEnchereByArticle(id);
		
		Article article = art.selectByIdArticle(id);
		Categorie categorie = cat.selectById(article.getCategorie().getIdCategorie());
		
		request.setAttribute("nomArticle", article.getNomArticle());
		request.setAttribute("description", article.getDescription());
		request.setAttribute("categorie", categorie.getLibelle());
		request.setAttribute("offre", article.getPrixVente());
		request.setAttribute("acheteur", article.getUtilisateur().getIdUtilisateur());
		request.setAttribute("miseAPrix", article.getPrixInitial());
		request.setAttribute("finEnchere", article.getDateFin());
		request.setAttribute("rue", article.getRue());
		request.setAttribute("codePostal", article.getCodePostal());
		request.setAttribute("ville", article.getVille());
		request.setAttribute("vendeur", article.getUtilisateur().getPseudo());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/encherir.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
