package ar.com.easytech.jook;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FilterStartup implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent ctx) {
		System.out.println("Adding tmpl filter...");
		ctx.getServletContext().addFilter("tmpl", TmplFilter.class);
		
	}

}
