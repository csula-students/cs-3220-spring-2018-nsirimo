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
    private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        doDelete(request, response);
        request.getRequestDispatcher("../WEB-INF/login.jsp").forward(request, response);
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