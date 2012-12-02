package ar.com.easytech.jook;

/*
 * This filter attempts to wrap the request inside a template that has been set for the html page.
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "jook", urlPatterns = {"*.html"})
public class TmplFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("TmplFilter destroy");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		//Checkeo que lleguen los xhtml
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		
		PrintWriter out = response.getWriter();
		TmplResponseWrapper wrapper = new TmplResponseWrapper(response);
		// Apply the filter chain
		chain.doFilter(servletRequest, wrapper);
		// Parse the document and extract includes and templates
		TemplateParser parser = new TemplateParser(wrapper.toString(), request);
		// Wrtie the output
		out.write(parser.parse());
		// Close the stream
		out.close(); 
		
	}
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("jook Filter init");
	}

}
