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

@SuppressWarnings("serial")
public class Register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/register.jsp");
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uname = req.getParameter("username");
		String pwd = req.getParameter("password");
		String fname = req.getParameter("Firstname");
		String lname = req.getParameter("Lastname");
		if (StringUtils.isEmpty(uname) || StringUtils.isEmpty(pwd)
				|| StringUtils.isEmpty(fname) || StringUtils.isEmpty(lname)) {
			req.setAttribute("errormsg", "Invalid credentials ");
			RequestDispatcher view = req.getRequestDispatcher("/error.jsp");
			view.forward(req, resp);
			return;
		}
		ServletContext context = getServletContext();
		User user = (User) context.getAttribute(uname);
		if (user == null) {
			// user does not exist hence add
			User newUser = new User(uname, Md5Util.md5(pwd), fname, lname);
			context.setAttribute(uname, newUser);
			req.setAttribute("loggedUser", newUser.getFirstName());
			RequestDispatcher view = req.getRequestDispatcher("/welcome.jsp");
			view.forward(req, resp);
		} else {
			// user exists and forward to error page
			req.setAttribute("errormsg", "User with id " + uname + " exists");
			RequestDispatcher view = req.getRequestDispatcher("/error.jsp");
			view.forward(req, resp);
		}

	}

}
