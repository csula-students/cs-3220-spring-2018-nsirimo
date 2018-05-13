package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.mysql.Database;


@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD = "add";
	private static final String DELETE = "delete";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Called");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		request.setAttribute("data", events);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AdminEvents.jsp");
		rd.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("status");
		if (ADD.equals(action)) {
			add(request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("add");
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		String event_name = request.getParameter("event_name");
		String description = request.getParameter("description");
		int trigger = Integer.parseInt(request.getParameter("trigger"));

		Event event = new Event(events.size(), event_name, description, trigger);
		dao.add(event);

		response.sendRedirect("/admin/events");
	}

}
