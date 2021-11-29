package fr.eni.encheres.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
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
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/detailUtilisateur.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		utilisateur = utilisateurManager.getUserById(Integer.parseInt(request.getParameter("userNumber")));
		request.setAttribute("user", utilisateur);
		doGet(request, response);
	}
	
}
