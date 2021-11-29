package fr.eni.encheres.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ChiffrementPwd;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletMyProfile
 */
@WebServlet(urlPatterns = { "/monProfil", "/supprimer", "/suppressionConfirmee" })
public class ServletMyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myProfilePage.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.contains("supprimer")) {
			request.setAttribute("demandeSuppression", "suppressionDemandee");
		}
		if (path.contains("suppressionConfirmee")) {
			deleteProfile(request, response);
			return;
		}
		doGet(request, response);
	}
	
	private void deleteProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
		if (ChiffrementPwd.SHAcrypted(request.getParameter("password")).equals(utilisateur.getMotDePasse())) {
			session.invalidate();
			utilisateurManager.supprimerUtilisateur(utilisateur);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("wrongPass", "wrong");
			doGet(request, response);
		}
	}
}
