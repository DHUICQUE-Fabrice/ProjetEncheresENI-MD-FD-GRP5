package fr.eni.encheres.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/encheres")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.contains("createAccount")) {
			createAccount
		}
		HttpSession session = request.getSession();

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
		doGet(request, response);
	}
	
}
