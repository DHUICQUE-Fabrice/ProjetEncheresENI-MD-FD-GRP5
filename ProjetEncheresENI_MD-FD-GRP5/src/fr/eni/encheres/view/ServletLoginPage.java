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
 * Servlet implementation class ServletTest
 */
@WebServlet("/login")
public class ServletLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur login = null;
		for (Utilisateur utilisateur : utilisateurManager.allUsers()) {
			if (request.getParameter("userName").equals(utilisateur.getPseudo())) {
				login = utilisateur;
			}
		}
		if (login == null) {
			request.setAttribute("unknown", "true");
		} else if (request.getParameter("userPassword").equals(login.getMotDePasse())) {
			System.out.println("Connecté en tant que " + login.toString());
		} else {
			request.setAttribute("wrongPass", "true");
		}
		doGet(request, response);
	}

}
