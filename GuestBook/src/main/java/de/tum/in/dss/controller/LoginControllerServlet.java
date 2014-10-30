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

public class LoginControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3925572908867133399L;

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
		String errorMessage = null;
		String username = req.getParameter("username");
		String encryptedPwd = PasswordEncryptionHelper.md5(req
				.getParameter("password"));

		User loggedInUser = null;
		HttpSession session = req.getSession();
		if (session != null
				&& session.getAttribute("userloggedin") != null
				&& ((Boolean) session.getAttribute("userloggedin"))
						.booleanValue()) {
			loggedInUser = (User) session.getAttribute("user");
		}

		if (username != null && encryptedPwd != null) {
			User user = new User(username, encryptedPwd);
			UserAuthentication authenticatedUsers = (UserAuthentication) getServletContext()
					.getAttribute("authenticatedusers");

			if (authenticatedUsers == null
					|| (authenticatedUsers != null && !authenticatedUsers
							.getUserMap().containsKey(username))) {
				errorMessage = "User not registered";
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher rd = req
						.getRequestDispatcher("./Loginerror.jsp");
				rd.forward(req, resp);
			} else if (authenticatedUsers.getUserMap().containsValue(user)) {
				// Redirect Authenticated User
				HttpSession session_new = req.getSession(true);
				// req.setAttribute("user", user);
				session_new.setAttribute("user", user);
				session_new.setAttribute("userloggedin", true);
				RequestDispatcher rd = req
						.getRequestDispatcher("./guestBookhome");
				rd.forward(req, resp);
			} else {
				// construct error message and redirect
				errorMessage = "Incorrect password";
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher rd = req
						.getRequestDispatcher("./Loginerror.jsp");
				rd.forward(req, resp);
			}
		} else if (loggedInUser != null && loggedInUser.getUserName() != null) {
			session.setAttribute("user", loggedInUser);
			session.setAttribute("userloggedin", true);
			RequestDispatcher rd = req.getRequestDispatcher("./guestBookhome");
			rd.forward(req, resp);
		} else if (req.getParameter("login") != null) {
			req.setAttribute("errorMessage",
					"Username and password field contains invalid characeters");
			RequestDispatcher rd = req.getRequestDispatcher("./Loginerror.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("./Login.jsp");
			rd.forward(req, resp);
		}

	}
}
