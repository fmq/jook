package ar.com.easytech.jook;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TmplResponseWrapper extends HttpServletResponseWrapper {

	private CharArrayWriter output;
	
    public String toString() {
        return output.toString();
    }
    public TmplResponseWrapper(HttpServletResponse response){
        super(response);
        output = new CharArrayWriter();
    }
    
    public PrintWriter getWriter(){
        return new PrintWriter(output);
    }

}