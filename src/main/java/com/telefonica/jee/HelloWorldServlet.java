package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");   //Se indica que la respuesta es HTML    
		PrintWriter out = response.getWriter(); //Se obtiene el stream de salida
		out.println("<html><head><title>Primer servlet</title></head>"
				+ "<body bgcolor=\"#ffbe86\"><div style='color:white; text-align:center; position:absolute; top:50%;"
				+ "left:50%; width:450px; margin-left:-225px; height:100px; margin-top:-50px; line-height:70px; border-style: groove;'>"
				+ "<p><h3>HELLO WORLD!</h3></p></div></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
