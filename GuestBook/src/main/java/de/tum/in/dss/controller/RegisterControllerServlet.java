/**
 * 
 */
package de.tum.in.dss.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import de.tum.in.dss.PasswordEncryptionHelper;
import de.tum.in.dss.User;
import de.tum.in.dss.UserAuthentication;

public class RegisterControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4722165427430458406L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String encryptedPassword = PasswordEncryptionHelper.md5(req
				.getParameter("password"));
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");

		UserAuthentication authenticatedUsers = (UserAuthentication) getServletContext()
				.getAttribute("authenticatedusers");

		User loggedInUser = null;
		HttpSession session = req.getSession();
		if (session != null
				&& session.getAttribute("userloggedin") != null
				&& ((Boolean) session.getAttribute("userloggedin"))
						.booleanValue()) {
			loggedInUser = (User) session.getAttribute("user");
		}

		if (authenticatedUsers == null) {
			authenticatedUsers = new UserAuthentication();

		}
		if (username != null && !username.trim().isEmpty()
				&& encryptedPassword != null
				&& !encryptedPassword.trim().isEmpty()) {
			User user = new User(username, firstname, lastname,
					encryptedPassword);

			if (authenticatedUsers.getUserMap().containsValue(user)) {
				// Redirect Authenticated User
				req.setAttribute("errorMessage", "User already registered");
				RequestDispatcher rd = req
						.getRequestDispatcher("./Loginerror.jsp");
				rd.forward(req, resp);
			} else {
				authenticatedUsers.addUser(user);
				getServletContext().setAttribute("authenticatedusers",
						authenticatedUsers);
				HttpSession session_new = req.getSession(true);
				// req.setAttribute("user", user);
				session_new.setAttribute("user", user);
				session_new.setAttribute("userloggedin", true);
				RequestDispatcher rd = req
						.getRequestDispatcher("./guestBookhome");
				rd.forward(req, resp);
			}
		} else if (loggedInUser != null && loggedInUser.getUserName() != null) {
			session.setAttribute("user", loggedInUser);
			session.setAttribute("userloggedin", true);
			RequestDispatcher rd = req.getRequestDispatcher("./guestBookhome");
			rd.forward(req, resp);
		} else {
			req.setAttribute("errorMessage",
					"Username and password field contains invalid characeters");
			RequestDispatcher rd = req.getRequestDispatcher("./Loginerror.jsp");
			rd.forward(req, resp);
		}
	}

}
