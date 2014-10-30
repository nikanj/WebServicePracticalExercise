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

public class MyFilter implements Filter {
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
			ServletContext sc = req.getSession().getServletContext();

			if (!req.getRequestURI().contains("final")) {
				resp.setContentType("text/html");

				String fname = req.getParameter("filename");

				if (fname == null) {
					urls.put("index", 1);
					sc.setAttribute("index", 1);
				} else {
					if (urls.containsKey(fname)) {

						int temp = urls.get(fname);
						temp = temp + 1;
						urls.put(fname, temp);
						sc.setAttribute(fname, temp);
					} else {
						urls.put(fname, 1);
						sc.setAttribute(fname, 1);
					}
				}

				req.getSession().setAttribute("hsMap", urls);
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
