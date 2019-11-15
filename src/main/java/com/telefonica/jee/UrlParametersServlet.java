package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UrlParametersServlet
 */
@WebServlet("/UrlParametersServlet")
public class UrlParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UrlParametersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// lee parametros de la peticion request
		System.out.println("\n");
		
		/* http://localhost:8080/servlets/UrlParametersServlet?firstname=paco&lastname=mer */
		PrintWriter out = response.getWriter();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		boolean isError = false;
		if (firstname == null || firstname.length() == 0) {
			out.println("BAD REQUEST: provide firstname parameter");
			isError = true;
		}
		if (lastname == null || lastname.length() == 0) {
			out.println("BAD REQUEST: provide lastname parameter");
			isError = true;
		}
		if (!isError) {
			out.println(request.getParameter("firstname"));
			out.println(request.getParameter("lastname"));	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
