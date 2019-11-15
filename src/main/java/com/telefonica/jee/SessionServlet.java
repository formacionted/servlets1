package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static final String HTTP_GET = "GET";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
	
		this.addHtmlHead(writer);
		
		if (request.getParameter("action") != null && request.getParameter("action").contentEquals("delete")) 
			this.invalidateSession(request.getSession(), writer);
		else if (request.getParameter("action") != null && request.getParameter("action").contentEquals("add")) 
			this.addAttribute(request, response);
		
		if (request.getMethod().equals(HTTP_GET)) {
			writer.append("GET request: " + request.getQueryString() + "	\r\n");
		}
		this.addHtmlFooter(writer);	
	}
	
	/**
	 * It invalidates current session
	 * @param request
	 */
	private void invalidateSession(HttpSession session, PrintWriter writer) {
		writer.append("	<p> Invalidated session</p> "+session.getId()+"\r\n");
		writer.append("	<p> Session created date</p> "+ this.getDate(session.getCreationTime())+"\r\n");
		writer.append("	<p> Session deteled date</p> "+ this.getDate(System.currentTimeMillis())+"\r\n");
		session.invalidate();
	}

	private String getDate(long lfecha){
		Instant instant = Instant.ofEpochMilli(lfecha);
		LocalDateTime fecha = LocalDateTime.ofInstant(instant,ZoneId.systemDefault()); 
		return fecha.format(DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy 'a las' hh:mm:ss"));
	}
	
	private void addHtmlFooter(PrintWriter writer) {
		writer.append("</div>" + this.getHtmlFooter() +"</body>\r\n</html>\r\n");
		
	}

	private void addHtmlHead(PrintWriter writer) {
		writer.append("<!DOCTYPE html>\r\n")
		.append("<html>\r\n")
		.append("		<head>\r\n")
		.append("			<title>Welcome message</title>\r\n")
		.append("			 <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n")
		.append("		</head>\r\n")
		.append("		<body>\r\n")
		.append("<h3>Attribute session</h3>")
		.append("<div >\n");
	}

	private void addAttribute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String attributeName = request.getParameter("attributeName");
		String attributeValue = request.getParameter("attributeValue");
		HttpSession session = request.getSession();
		
		if (attributeName != null && !attributeName.trim().isEmpty() && attributeValue != null && !attributeValue.trim().isEmpty()) 
			session.setAttribute(attributeName, attributeValue);
		
		List<String> attributeNames = Collections.list(session.getAttributeNames());
		if (attributeNames.isEmpty()) {
			writer.append("<p> No attributes found.</p>\r\n");
		}
		for (String name : attributeNames) {
			writer.append("<p>" + name + " " + session.getAttribute(name) + "</p>\r\n");
		}	
	}

	private String getHtmlFooter() {
		return "<footer><a href=\"/servlets/session.html\">Go Back</a></footer>";
	}

}
