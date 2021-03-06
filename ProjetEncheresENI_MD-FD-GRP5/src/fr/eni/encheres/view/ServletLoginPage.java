package fr.eni.encheres.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ChiffrementPwd;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/login")
public class ServletLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("rememberMe")) {
				request.setAttribute("userRemembered", cookie.getValue());
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur login = null;
		for (Utilisateur utilisateur : utilisateurManager.allUsers()) {
			String userName = request.getParameter("userName");
			if (userName.equals(utilisateur.getPseudo()) || userName.equals(utilisateur.getEmail())) {
				login = utilisateur;
			}
		}
		String passwordtoCheck = ChiffrementPwd.SHAcrypted(request.getParameter("userPassword"));
		if (login == null) {
			request.setAttribute("unknown", "true");
		} else if (passwordtoCheck.equals(login.getMotDePasse())) {
			if (request.getParameter("rememberMe") != null && request.getParameter("rememberMe").equals("rememberMe")) {
				Cookie cookieRM = new Cookie("rememberMe", login.getPseudo());
				cookieRM.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cookieRM);
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", login);
			response.sendRedirect("encheres");
			return;
		} else {
			request.setAttribute("wrongPass", "true");
		}
		doGet(request, response);
	}
	
}
