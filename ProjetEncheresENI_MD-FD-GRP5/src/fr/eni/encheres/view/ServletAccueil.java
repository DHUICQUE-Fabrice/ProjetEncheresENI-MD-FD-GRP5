package fr.eni.encheres.view;

import java.io.IOException;
import java.util.ArrayList;
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
import fr.eni.encheres.bo.Utilisateur;

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
		request.setCharacterEncoding("UTF-8");
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
		request.setCharacterEncoding("UTF-8");
		String filtreTexte = request.getParameter("filtre");
		Boolean achat = Boolean.valueOf((request.getParameter("achatRadio")));
		List<Article> articles = new ArrayList<Article>();
		
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String encheresEnCours = request.getParameter("encheresEnCours");
		String encheresRemportees = request.getParameter("encheresRemportees");
		String ventesEnCours = request.getParameter("ventesEnCours");
		String ventesNonDebutees = request.getParameter("ventesNonDebutees");
		String ventesTerminees = request.getParameter("ventesTerminees");
		
		CategorieManager categorieManager = new CategorieManager();
		if (request.getParameter("categorie").contains("all")) {
			articles.addAll(rechercherArticles(request, filtreTexte, null, achat, encheresOuvertes, encheresEnCours,
					encheresRemportees, ventesEnCours, ventesNonDebutees, ventesTerminees));
		} else {
			Categorie categorie = categorieManager.selectById(Integer.parseInt(request.getParameter("categorie")));
			articles.addAll(rechercherArticles(request, filtreTexte, categorie, achat, encheresOuvertes,
					encheresEnCours, encheresRemportees, ventesEnCours, ventesNonDebutees, ventesTerminees));
		}
		request.setAttribute("articles", articles);
		
		List<Categorie> categories = categorieManager.selectAll();
		request.setAttribute("categories", categories);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private List<Article> rechercherArticles(HttpServletRequest request, String filtreText, Categorie categorie,
			boolean achat, String... paramsChecked) {
		List<Article> articles = new ArrayList<>();
		HttpSession session = request.getSession();
		ArticleManager articleManager = new ArticleManager();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
		if (categorie == null) {
			articles = articleManager.allArticle();
		} else {
			articles = articleManager.allArticleByCategorie(categorie.getIdCategorie());
		}
		//Si l'utilisateur est connecté, on enlève de la liste soit ses propres articles si on est en mode achat, sinon, l'inverse.
		if (utilisateur != null) {
			if (achat) {
				// Obligé de passer par une boucle for classique plutôt qu'un foreach pour éviter "java.util.ConcurrentModificationException"
				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getUtilisateur().getIdUtilisateur() == utilisateur.getIdUtilisateur()) {
						articles.remove(i);
					}
				}
			} else {
				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getUtilisateur().getIdUtilisateur() != utilisateur.getIdUtilisateur()) {
						articles.remove(i);
					}
				}
			}
		}
		System.out.println(articles.size());
		//Si l'utilisateur a rentré un texte, on filtre sur ce texte les résultats
		if (!filtreText.equals("")) {
			filtreText = filtreText.toLowerCase().trim();
			List<Article> articlesTemp = articles;
			for (int i = 0; i < articles.size(); i++) {
				if (!articles.get(i).getNomArticle().toLowerCase().contains(filtreText)
						&& !articles.get(i).getDescription().toLowerCase().contains(filtreText)) {
					articlesTemp.set(i, null);
				}
			}
			articles = new ArrayList<Article>();
			for (Article article : articlesTemp) {
				if (article != null) {
					articles.add(article);
				}
			}
		}
		
		return articles;
	}
}
