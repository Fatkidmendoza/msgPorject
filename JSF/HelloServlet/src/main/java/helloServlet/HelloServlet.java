/**
 * 
 */
package helloServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fodorm
 * 
 */
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 3482037667566115081L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String message = request.getParameter("helloText");

		out.println("<h1> Hello, " + message + "</h1>");
		out.println("<a href='.'>go back!</a>");
	}
}
