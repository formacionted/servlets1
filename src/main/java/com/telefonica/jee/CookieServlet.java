package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
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
		// client example https://www.avajava.com/tutorials/lessons/how-do-i-send-cookies-from-a-client-to-a-servlet.html
		PrintWriter writer = response.getWriter();
		String cookieName = request.getParameter("cookie");
		String cookieValue = request.getParameter("value");
		int cookieDuration;
		try {                     
			cookieDuration = Integer.parseInt(request.getParameter("duration"));
		} catch (NumberFormatException e) {
			System.out.println("Error");
			cookieDuration = -1;
		}
		response.setContentType("text/html;    charset=UTF-8");
		
		this.addHtmlHead(writer);
		
		if (cookieName != null && !cookieName.trim().isEmpty() && cookieValue != null && !cookieValue.trim().isEmpty()) {
			Cookie nuevaCookie = new Cookie(cookieName, cookieValue);
			nuevaCookie.setMaxAge(cookieDuration);
			response.addCookie(nuevaCookie);
		}

		List<Cookie> cookies = request.getCookies() != null ? Arrays.asList(request.getCookies()) : new ArrayList<Cookie>();
		if (cookies.isEmpty()) {
			writer.println("<p> No cookies found. </p>\r\n");
		}
		for (Cookie cookie : cookies) {
			writer.println("Cookie " + cookie.getName() + ", value: " + cookie.getValue() + "\r\n");
		}
		
		this.addHtmlFooter(writer);	
	}

	
	private String getDate(long lfecha) {
		Instant instant = Instant.ofEpochMilli(lfecha);
		LocalDateTime fecha = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return fecha.format(DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy 'a las' hh:mm:ss"));
	}

	private void addHtmlFooter(PrintWriter writer) {
		writer.append("</div>" + this.getHtmlFooter() + "</body>\r\n</html>\r\n");

	}

	private void addHtmlHead(PrintWriter writer) {
		writer.append("<!DOCTYPE html>\r\n").append("<html>\r\n").append("		<head>\r\n")
				.append("			<title>Welcome message</title>\r\n")
				.append("			 <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n")
				.append("		</head>\r\n").append("		<body>\r\n").append("<h3>Cookie page</h3>")
				.append("<div >\n");
	}


	private String getHtmlFooter() {
		return "<footer><a href=\"/servlets/cookies.html\">Go Back</a></footer>";
	}
}
