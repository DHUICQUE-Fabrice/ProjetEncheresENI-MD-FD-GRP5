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
import fr.eni.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ServletCreateAccount
 */
@WebServlet("/createAccount")
public class ServletCreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String motDePasse = ChiffrementPwd.SHAcrypted(request.getParameter("password"));
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					motDePasse, 0, false);
			request.setAttribute("user", utilisateur);
			if (!request.getParameter("password").equals(request.getParameter("confirmation"))) {
				request.setAttribute("wrongConfirmation", "La confirmation du mot de passe ne correspond pas");
				doGet(request, response);
				return;
			}
			System.out.println(utilisateur.toString());
			
			utilisateur = utilisateurManager.ajouter(utilisateur);
			HttpSession session = request.getSession();
			session.setAttribute("user", utilisateur);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreurs", e.getListeCodesErreurs());
			doGet(request, response);
		}
		
	}
	
}
