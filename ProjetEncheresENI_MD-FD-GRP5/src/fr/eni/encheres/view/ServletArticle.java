package fr.eni.encheres.view;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/article")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "img";
	private static final String DEFAULT_IMG = "no-image-found-360x250.png";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		request.setAttribute("categorie", cat.selectAll());
		
		System.out.println("je suis dans la méthode doGet servlet article");
		String id = request.getParameter("idarticle");
		if (id == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ajouterArticle.jsp");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("je suis dans le else quand id != null");
			// art.selectByIdArticle(Integer.parseInt(id));
			art.selectByIdArticle(Integer.parseInt(request.getParameter("article")));
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/modifierArticle.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String appPath = request.getServletContext().getRealPath("");
		String uploadFilePath = appPath + UPLOAD_DIR;
		
		System.out.println("je suis dans le doPost ServletArticle");
				
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		
		String fileName = null;
		for (Part part : request.getParts()) {
			if (part.getName().equals("image")) {
				fileName = getFileName(part);
				if (!fileName.equals(DEFAULT_IMG)) {
					part.write(uploadFilePath + File.separator + fileName);
				}
				request.setAttribute("urlImage", UPLOAD_DIR + File.separator + fileName);
			}
		}
		
		String action = request.getParameter("action");
		if (action.equals("ajouter")) {
			if (request.getParameter("idArticle") == null) {
				ajouterArticle(request, response);
			} else {
				modifierArticle(request, response);
			}
			
		} else if (action.equals("delete")) {
			deleteArticle(request, response);
		} else if (action.equals("annuler")) {
		}
		
		response.sendRedirect("encheres");
		
	}
	
	private void ajouterArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Article article = new Article();
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		HttpSession session = request.getSession();
		
		article.setNomArticle(request.getParameter("nomArticle"));
		article.setDescription(request.getParameter("description"));
		article.setCategorie(cat.selectById(Integer.parseInt(request.getParameter("categorie"))));
		article.setUrlImage(String.valueOf(request.getAttribute("urlImage")));
		article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
		article.setDateDebut(LocalDate.parse(request.getParameter("debutEnchere")));
		article.setDateFin(LocalDate.parse(request.getParameter("finEnchere")));
		article.setRue(request.getParameter("rue"));
		article.setCodePostal(Integer.parseInt(request.getParameter("codePostal")));
		article.setVille(request.getParameter("ville"));
		article.setUtilisateur((Utilisateur) session.getAttribute("user"));
		try {
			art.ajouter(article);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
		}
	}
	
	private void modifierArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Article article = new Article();
		CategorieManager cat = new CategorieManager();
		ArticleManager art = new ArticleManager();
		HttpSession session = request.getSession();
		
		System.out.println("je suis dans la méthode modifier article");
		try {
			
			article.setIdArticle(Integer.parseInt(request.getParameter("idarticle")));
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
			
			art.updateArticle(article);
			
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
		}
	}
	
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ArticleManager art = new ArticleManager();
		
		int id = Integer.parseInt(request.getParameter("idArticle"));
		try {
			art.deleteArticle(id);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
		}
	}
	
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				if (token.length() < 15) {
					return DEFAULT_IMG;
				}
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return DEFAULT_IMG;
	}
}
