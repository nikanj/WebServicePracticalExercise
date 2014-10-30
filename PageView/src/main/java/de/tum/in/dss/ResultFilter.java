package de.tum.in.dss;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultFilter implements Filter {
	private ServletContext context;
	int access_count;
	String page;
	static Map<String, Integer> urls = new HashMap();

	public void init(FilterConfig filterconfig) throws ServletException {

		access_count = 0;

		page = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			Map<String, Integer> urls = (Map<String, Integer>) req.getSession()
					.getAttribute("hsMap");

			if (req.getRequestURI().contains("final")) {
				resp.setContentType("text/html");

				PrintWriter out = resp.getWriter();
				Set kv = urls.entrySet();
				Iterator itr = kv.iterator();

				out.println("<html><body>");
				out.println("<h1>Statistical Access Information</h1>");
				out.println("<table>");
				while (itr.hasNext()) {
					Map.Entry val = (Map.Entry) itr.next();
					page = (String) val.getKey();
					access_count = (Integer) val.getValue();
					out.println("<tr>");
					out.println("<td>");
					out.println(page);
					out.println("</td>");
					out.println("<td>");
					out.println("&nbsp;");
					out.println("</td>");
					out.println("<td>");
					out.println(access_count);
					out.println("</td>");
					out.println("</tr>");

				}
				out.println("</table>");
				out.println("<br>");
				out.println("<b>Click on the link to go to the index page: <a href=\"http:index.jsp?filename=index\">link</a></b>");
				out.println("</body></html>");

				req.getSession().setAttribute("hsMap", urls);

				System.out.println(urls);
				chain.doFilter(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

		// This is optional step but if you like you
		// can write hitCount value in your database.
	}
}
