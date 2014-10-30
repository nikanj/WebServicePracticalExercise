/**
 * 
 */
package de.tum.in.dss;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;


public class EscapingFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		checkAllParameters((HttpServletRequest)request);
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}
	
	@SuppressWarnings("unchecked")
	public void checkAllParameters(HttpServletRequest request){
		Map<String,String[]> map = (Map<String,String[]>) request.getParameterMap();
		
		for(String key : map.keySet()){
		    String keyStr = key;
		    String[] vals = map.get(keyStr);
		    
		    System.out.println("Before:: Key " + (String)key + "     :    " + vals[0]);
		    
		    // Apply all escape checks
		    vals[0] = allEscapeCheck(vals[0]);
		    
		    System.out.println("After:: Key " + (String)key + "     :    " + vals[0]);
		   
		}
	}
	
	public String allEscapeCheck(String parameterValue){
		 String results = StringEscapeUtils.unescapeHtml4(parameterValue);
		 results = StringEscapeUtils.unescapeJava(results);
		 results = StringEscapeUtils.unescapeXml(results);
		 results = StringEscapeUtils.unescapeEcmaScript(results);
		 results = StringEscapeUtils.unescapeCsv(results);
		 results = StringEscapeUtils.unescapeHtml3(results);
		 
		 return results;
	}

}
