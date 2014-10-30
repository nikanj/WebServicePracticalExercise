package de.tum.ibis.wsc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MathServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String a=req.getParameter("InputNumber");
	    String b=req.getParameter("InputNumber2");
	    int c=Integer.parseInt(a);
	    int d=Integer.parseInt(b);
	    String cal = req.getParameter("Operations");
	    float e = 0;
	    if(cal.equals("add"))
	    	e=c+d;
	    else if(cal.equals("sub"))
	    	e=c-d;
	    else if(cal.equals("mul"))
	    	e=c*d;
	    else
	    	e=(float)c/d;
	    req.setAttribute("result", e);
	    req.setAttribute("result1", c);
	    req.setAttribute("result2", d);
	    req.setAttribute("result3", cal);
	    RequestDispatcher view=req.getRequestDispatcher("solution.jsp");
		view.forward(req,resp);		
	}
}
