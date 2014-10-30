package de.tum.in.dss;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dss.model.User;
import de.tum.in.dss.util.Md5Util;
import de.tum.in.dss.util.StringUtils;

public class LoginForm extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5462128639330252898L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/login.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uname = req.getParameter("username");
		String pwd = req.getParameter("password");
		if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(pwd)) {
			req.setAttribute("errormsg", "Invalid credentials ");
			RequestDispatcher view = req.getRequestDispatcher("/error.jsp");
			view.forward(req, resp);
			return;
		}

		ServletContext context = getServletContext();
		User user = (User) context.getAttribute(uname);
		if (user != null) {
			// check password
			String md5password = Md5Util.md5(pwd);

			if (md5password.equals(user.getPassword())) {
				// go to the welcome page
				req.setAttribute("loggedUser", user.getFirstName());
				RequestDispatcher view = req
						.getRequestDispatcher("/welcome.jsp");
				view.forward(req, resp);
			} else {
				req.setAttribute("errormsg", "Invalid credentials ");
				RequestDispatcher view = req.getRequestDispatcher("/error.jsp");
				view.forward(req, resp);
			}
		} else {
			// redirect to error page
			// possible registration also
			req.setAttribute("errormsg", "User does not exist. Please register");
			RequestDispatcher view = req.getRequestDispatcher("/error.jsp");
			view.forward(req, resp);
		}
	}
}
