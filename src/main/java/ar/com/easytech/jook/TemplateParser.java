package ar.com.easytech.jook;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TemplateParser {

	Document doc;
	String baseUrl;
	
	
	public TemplateParser(String html, HttpServletRequest request) {
		this.baseUrl = Util.getBaseUrl(request);
		this.doc = Jsoup.parse(html);
	}
	
	public String parse() {
		
		// We need to check to see if the html has a template.
		// This is what we check first.
		Element templateDef = doc.select("tmpl|template").first();
		
		if (templateDef != null)
				parseTemplate(templateDef);
		//Onece the template is applied we parse ot includes
		parseIncludes();
		//We return the html
		return doc.html();
		
		
	}
	
	public void parseTemplate(Element templateDef) {
		
		// Fetch the tempalte
		String src = templateDef.attr("src");
		//Load the template into a document
		String html = "";
		try {
			html = Util.fetchContent(src, baseUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		// We need to find the name of the content in the document
		String contentName = getContentName();
		
		if (contentName == null)
			return;
		
		// Create the document
		Document template = Jsoup.parse(html);
		//We Parse the holders, we actually only need to
		// check if there is one for this particular document
		doc = parseHolders(template, contentName);
		
		
	}
	
	public String getContentName() {
		Elements content = doc.select("tmpl|content");
		
		if (content == null)
			return null;
		
		return content.attr("name");
		
	}
	
	public String getContent(String name) {
		
		Elements content = doc.select("tmpl|content");
		if (content == null)
			return null;
		
		if (name.equals(content.attr("name")))
			return content.html();
		
		return null;
	}
	
	public Document parseHolders(Document template, String contentName) {
		
		Elements holders = template.select("tmpl|holder");
		
		if (holders.size() == 0)
			return null;
		
		for (int i=0; i < holders.size(); i++) {
			Element holder = holders.get(i);
			String name = holders.get(i).attr("name");
			
			if (name.equals(contentName)) {
				// We need to merge the documents into a single document. 
				// we replace the holder node with the document body
				holder.before(getContent(contentName));
				holder.remove();
			}
		}
		
		return template;
	}
	
	public void parseIncludes() {
		
		Elements includes = doc.select("tmpl|include");
		// If we don't have to replace anything we simple return the html
		if (includes.size() == 0)
			return;
		
		// Loop the elements
		for (int i=0 ; i < includes.size() ; i++) {
			Element include = includes.get(i);
			String src = include.attr("src");
			String target = (include.attr("target") != null ? include.attr("target") : "body"); 
			// Skip if null
			if (src != null) {
				String html = "";
				try {
					html = Util.fetchContent(src, baseUrl);
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				
				if (target.equalsIgnoreCase("head")) {
					// Check if we have the head tag
					doc.head().append(html);
				} else
					include.before(html);
				include.remove();	
			}		
		}
	}
}
