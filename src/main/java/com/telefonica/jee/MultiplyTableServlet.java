package com.telefonica.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultiplyTableServlet
 */
@WebServlet("/MultiplyTableServlet")
public class MultiplyTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiplyTableServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* https://javatutorial.net/java-servlet-post-example */
		
		// Imprime una tabla de multiplicar para el numero obtenido por parametro en un formulario en multiplyTable.html
		PrintWriter writer = response.getWriter();
		String number = request.getParameter("number");
		if (number == null || number.length() == 0) {
			writer.println("BAD REQUEST: provide number parameter");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		writer.append("<!DOCTYPE html>\r\n").append("<html>\r\n").append("		<head>\r\n")
				.append("			<title>Welcome message</title>\r\n")
				.append("			 <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n")

				.append("		</head>\r\n").append("		<body>\r\n")

				.append("<div >\n")
				.append("  <table>\n")
				.append("    <tr>\n");
		
				for (int i = 1; i <= 10; i++) {
					writer.append("      <th>"+i+"</th>\n");
				}
		
				writer.append("    </tr>\n")
				.append("    <tr>\n");
				
				for (int i = 1; i <= 10; i++) {
					writer.append("      <td>"+i * Integer.valueOf(number)+"</td>\n");
				}
				
				writer.append("    </tr>\n")
				.append("  </table>\n")
				.append("</div>");

		writer.append("		</body>\r\n</html>\r\n");
	}

}
