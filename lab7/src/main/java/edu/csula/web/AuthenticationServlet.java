package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>LOGIN</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<main>");
		out.println("<h1> Doge Coin </h1>");
		out.println("<form method=\"POST\" id=\"loginForm\">");
		out.println("<label>Username: </label>");
		out.println("<div><input type=\"text\" id=\"username\" name=\"username\"/></div>");

		out.println("<label>Password: </label>");
		out.println("<div><input type=\"text\" id=\"username\" name=\"password\"/></div>");
		out.println("<button> LOGIN </button>");
		out.println("</form>");
		out.println("</main>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAO dao = new UsersDAOImpl(request.getSession());
		if (dao.authenticate(request.getParameter("username"), request.getParameter("password"))) {
			System.out.println("you got in bro!");
			response.sendRedirect("../admin/events");
		} else {
			response.sendRedirect("../admin/auth");
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersDAO dao = new UsersDAOImpl(request.getSession());
		dao.logout();
	}
}
