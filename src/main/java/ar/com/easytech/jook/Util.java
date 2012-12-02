package ar.com.easytech.jook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String fetchContent(String srcName, String baseUrl) throws IOException {
		
		if (!srcName.startsWith("/"))
			srcName = "/" + srcName;
		
		InputStream input = new URL( baseUrl +  srcName).openStream();
		
		StringBuilder tmpl = new StringBuilder();
		try {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	tmpl.append(line + "\n");
		    }
		} finally {
		    if (input != null) input.close();
		}
		
		return tmpl.toString();
		
	}

	public static String getBaseUrl( HttpServletRequest request ) {
	    if ( ( request.getServerPort() == 80 ) ||
	         ( request.getServerPort() == 443 ) )
	      return request.getScheme() + "://" +
	             request.getServerName() +
	             request.getContextPath();
	    else
	      return request.getScheme() + "://" +
	             request.getServerName() + ":" + request.getServerPort() +
	             request.getContextPath();
	}
	
}
