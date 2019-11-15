package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParametersServlet
 */
@WebServlet(name = "parametersServlet", urlPatterns = {"/ParametersServlet"},
initParams = {@WebInitParam(name="marco", value="polo")}
)
public class ParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParametersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* http://localhost:8080/servlets/ParametersServlet */
		PrintWriter out = response.getWriter();
		out.println(this.getServletConfig().getInitParameter("marco"));
		out.println(this.getServletContext().getInitParameter("email"));
		out.println("================== getInitParameterNames:");
		Enumeration<String> paramNames = this.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			out.println(paramName);
			String paramValue = this.getInitParameter(paramName);
			if (paramValue.length() == 0)
				out.print("no value");
			else
				out.print(paramValue);
		}

		out.println("============ getHeaderNames:");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			out.println(headerName + " " + request.getHeader(headerName));
		}

		out.println("============ getServletInfo: ");
		out.println(this.getServletInfo());
		out.println(" ============ getServletName: ");
		out.println(this.getServletName());
		out.println("============ strFecha: ");
//		Instant instant = Instant.ofEpochMilli(getLastModified(request));
//		LocalDateTime fecha = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//		String strFecha = fecha.format(DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy 'a las' hh:mm:ss"));
//		out.println(strFecha);
//		response.addHeader("Cache-Control", "no-cache");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public String getServletInfo(){
		return "Copyright 2019";
	}  
	  

    @Override
    protected long getLastModified (HttpServletRequest req) {
        return getLastModifiedMillis();
    }

    /**
     * @see https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/last-modified-header.html
     * @see https://www.logicbig.com/quick-info/web/last-modified-and-if-modified-since.html
     * @return
     */
    private static long getLastModifiedMillis () {
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(2017, 1, 1, 10, 30, 20), ZoneId.of("GMT"));
        return zdt.toInstant().toEpochMilli();
    }

}
