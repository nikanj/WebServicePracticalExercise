package de.tum.in.dss;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReverseString extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String text = req.getParameter("inputText");
		int lt = text.length();
		StringBuilder rev = new StringBuilder();

		for (int i = lt - 1; i >= 0; i--) {
			rev.append(text.charAt(i));
		}

		req.setAttribute("originalText", text);
		req.setAttribute("reverseText", rev.toString());
		RequestDispatcher view=req.getRequestDispatcher("result.jsp");
		view.forward(req,resp);
	}
}
