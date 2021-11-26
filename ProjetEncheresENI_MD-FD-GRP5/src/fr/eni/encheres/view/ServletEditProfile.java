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
 * Servlet implementation class ServletEditProfile
 */
@WebServlet("/editProfile")
public class ServletEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/editProfile.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
			if (ChiffrementPwd.SHAcrypted(request.getParameter("oldPassword")).equals(utilisateur.getMotDePasse())) {
				if (!request.getParameter("pseudo").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "pseudo", request.getParameter("pseudo"));
				}
				if (!request.getParameter("nom").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "nom", request.getParameter("nom"));
				}
				if (!request.getParameter("prenom").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "prenom", request.getParameter("prenom"));
				}
				if (!request.getParameter("email").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "eMail", request.getParameter("email"));
				}
				if (!request.getParameter("telephone").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "telephone", request.getParameter("telephone"));
				}
				if (!request.getParameter("rue").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "rue", request.getParameter("rue"));
				}
				if (!request.getParameter("codePostal").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "codePostal",
							request.getParameter("codePostal"));
				}
				if (!request.getParameter("ville").equals(utilisateur.getPseudo())) {
					utilisateurManager.modifierUtilisateur(utilisateur, "ville", request.getParameter("ville"));
				}
			} else {
				request.setAttribute("wrongPass", "wrong");
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
}
