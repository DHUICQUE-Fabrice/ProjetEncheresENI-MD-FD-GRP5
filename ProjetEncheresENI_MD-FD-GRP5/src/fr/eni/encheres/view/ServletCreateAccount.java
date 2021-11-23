package fr.eni.encheres.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ChiffrementPwd;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreateAccount
 */
@WebServlet("/createAccount")
public class ServletCreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/createAccount.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		boolean pebkac = false;
		if (!request.getParameter("password").equals(request.getParameter("confirmation"))) {
			request.setAttribute("wrongConfirmation", "wrong");
			pebkac = true;
		}

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = ChiffrementPwd.SHAcrypted(request.getParameter("password"));

		List<Utilisateur> utilisateurs = utilisateurManager.allUsers();
		for (Utilisateur utilisateur : utilisateurs) {
			if (utilisateur.getPseudo().equals(pseudo)) {
				request.setAttribute("pseudoExists", "true");
				pebkac = true;
			}
			if (utilisateur.getEmail().equals(email)) {
				request.setAttribute("emailExists", "true");
				pebkac = true;
			}
		}
		if (pebkac) {
			doGet(request, response);
			return;
		}

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse, 0, false);
		System.out.println(utilisateur.toString());
		utilisateurManager.ajouter(utilisateur);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginPage.jsp");
		requestDispatcher.forward(request, response);
	}

}
